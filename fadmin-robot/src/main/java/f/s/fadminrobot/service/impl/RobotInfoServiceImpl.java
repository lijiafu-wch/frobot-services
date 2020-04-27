package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.fadminrobot.filter.CommonConfig;
import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.RobotInfoService;
import f.s.frobot.config.RobotConfig;
import f.s.frobot.config.RobotIdConfig;
import f.s.frobot.dao.RobotCategoryRelationMapper;
import f.s.frobot.dao.RobotInfoMapper;
import f.s.frobot.fenum.RobotTypeEnum;
import f.s.frobot.model.RobotCategoryRelation;
import f.s.frobot.model.RobotCategoryRelationExample;
import f.s.frobot.model.RobotInfo;
import f.s.frobot.model.RobotInfoExample;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.model.aliyun.ResponseResult;
import f.s.frobot.model.aliyun.Solution;
import f.s.fadminrobot.util.AutoPage;
import f.s.frobot.util.GsonUtil;
import f.s.frobot.util.PageUtil;
import f.s.frobot.util.Validators;
import f.s.frobot.vo.PageVo;
import f.s.frobot.vo.RobotInfoVo;
import f.s.idgenerator.TypeIdGenerator;
import f.s.jerror.BaseError;
import f.s.utils.DateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author lijiafu
 * @date 2020/2/19 10:49
 * @since 1.0
 */
@Service
@Slf4j
public class RobotInfoServiceImpl implements RobotInfoService {
    @Autowired
    private RobotInfoMapper robotInfoMapper;
    @Autowired
    private RobotCategoryRelationMapper robotCategoryRelationMapper;
    @Autowired
    @Qualifier("robotId")
    private TypeIdGenerator robotId;
    @Autowired
    private RobotIdConfig robotIdConfig;
    @Autowired
    private RobotConfig robotConfig;
    @Autowired
    private FrobotErrors frobotErrors;
    @Autowired
    private RobotInfoService robotInfoService;
    @Autowired
    private BotService botService;

    @Override
    @AutoPage
    public PageVo list(RobotInfoVo robotInfoVo, PageVo pageVo) throws BaseError {
        RobotInfoExample.Criteria createCriteria =
                new RobotInfoExample().orderBy(RobotInfo.Column.ctime.desc()).createCriteria();
        if(!Validators.isEmpty(robotInfoVo.getName())) createCriteria.andNameLike("%"+robotInfoVo.getName()+"%");
        if(!Validators.isEmpty(robotInfoVo.getType())) createCriteria.andTypeEqualTo(robotInfoVo.getType());
        //判断查询自己机器人
        if(null != CommonConfig.getBaseUserId() && CommonConfig.getBaseUserId() != 1){
            createCriteria.andBaseUserIdEqualTo(CommonConfig.getBaseUserId());
        }

        List<RobotInfo> list = robotInfoMapper.selectByExample(createCriteria.example());
        list.stream().forEach(robotInfo -> {
            robotInfo.setInstanceId(null);
        });
        PageVo page =  PageUtil.processingData(pageVo, list);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RobotInfoVo robotInfoVo) throws Exception {
        long id = robotId.generateId(robotIdConfig.getTypeCourse(), robotIdConfig.getDc());
        RobotInfo robotInfo = new RobotInfo();
        BeanUtils.copyProperties(robotInfoVo,robotInfo);
        robotInfo.setId(id);
        robotInfo.setCtime(new Date());
        robotInfo.setUtime(new Date());

        //调用阿里接口创建机器人
        ResponseResult responseResult = botService.createBot(robotInfoVo.getName(),robotInfoVo.getLanguageCode(),robotConfig.getTimeZone(),
                RobotTypeEnum.getValue(robotInfoVo.getType().intValue()));
        //机器人id
        String instanceId = responseResult.getInstanceId();
        log.info("create robot id is {} ",instanceId);
        robotInfo.setInstanceId(instanceId);
        int i = robotInfoMapper.insertSelective(robotInfo);
        if(0==i) throw frobotErrors.Errors("robotinfo save fail");

        //创建知识类目
        ResponseResult category = botService.createCategory(-1l, robotInfoVo.getName());
        //绑定知识类目
        List<Long> categoryList = new ArrayList<>();
        categoryList.add(category.getCategoryId());
        botService.linkBotCategory(instanceId,categoryList);
        //数据库关联知识类目
        RobotCategoryRelation robotCategoryRelation = new RobotCategoryRelation();
        robotCategoryRelation.setCtime(new Date());
        robotCategoryRelation.setRobotId(id);
        robotCategoryRelation.setCategoryId(category.getCategoryId().intValue());
        robotCategoryRelationMapper.insertSelective(robotCategoryRelation);
    }

    /**
     * 编辑机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(RobotInfoVo robotInfoVo) throws Exception {
        RobotInfo r = robotInfoMapper.selectByPrimaryKey(robotInfoVo.getId());
        RobotInfo robotInfo = new RobotInfo();
        BeanUtils.copyProperties(robotInfoVo,robotInfo);
        robotInfo.setUtime(new Date());
        int i = robotInfoMapper.updateByPrimaryKeySelective(robotInfo);
        if(0==i) throw frobotErrors.Errors("robotinfo edit fail");

        //验证欢迎语是否变化
        if(!Validators.isEmpty(robotInfoVo.getWelcome()) && !robotInfoVo.getWelcome().equals(r.getWelcome())){
            RobotCategoryRelationExample example = new RobotCategoryRelationExample();
            example.createCriteria().andRobotIdEqualTo(r.getId());
            RobotCategoryRelation robotCategoryRelation = robotCategoryRelationMapper.selectOneByExampleSelective(example,
                    RobotCategoryRelation.Column.categoryId);
            if(null == robotCategoryRelation){
                throw frobotErrors.CategoryNotRelation();
            }
            //查询欢迎语是否存在，判断新增/修改
            boolean flag = true;//true 新增，false 修改
            //查询知识列表，不为空，则存在"欢迎"的知识
            QueryKnowledges queryKnowledges =
                    botService.queryKnowledges(robotCategoryRelation.getCategoryId().longValue(),null,"欢迎",1,1);
            List<Knowledge> knowledges = queryKnowledges.getKnowledges();
            Long knowledgeId = null;
            if(null != knowledges && knowledges.size()>0){
                flag = false;
                knowledgeId = knowledges.get(0).getKnowledgeId();
            }
            //编辑欢迎知识库，flag为 true 新增，false 修改
            getWelcomeKnowledge(knowledgeId,robotCategoryRelation.getCategoryId().longValue(),robotInfoVo.getWelcome(),flag);
        }
        //验证标题是否变化
        if(!robotInfoVo.getName().equals(r.getName())){
            //调用阿里接口编辑机器人
            botService.updateBot(robotInfoVo.getName(),r.getInstanceId());
        }

    }

    /**
     * 拼装欢迎语，知识实体
     * @param knowledgeId 知识id
     * @param categoryId 类目id
     * @param welcome 欢迎语
     * @param flag true 新增  false 修改
     * @author lijiafu
     * @date 2020/3/2 9:52
     */
    private void getWelcomeKnowledge(Long knowledgeId,Long categoryId,String welcome,boolean flag) throws Exception{
        Knowledge knowledge = new Knowledge();
        knowledge.setKnowledgeId(knowledgeId);
        if(flag)knowledge.setCategoryId(categoryId);
        knowledge.setKnowledgeTitle("欢迎");
        Solution solution = new Solution();
        solution.setContent(welcome);
        if(!flag)solution.setAction("UPD");
        List<String> perList = new ArrayList<>();
        perList.add("1001");
        solution.setPerspectiveIds(perList);
        List<Solution> solutionList = new ArrayList<>();
        solutionList.add(solution);
        knowledge.setSolutions(solutionList);
        if(flag){
            List<String> coreWordList = new ArrayList<>();
            coreWordList.add("欢迎");
            knowledge.setCoreWords(coreWordList);
        }
        String json = GsonUtil.getGsonInstance().toJson(knowledge);
        if(flag){
            botService.createKnowledge(json);
        }else{
            botService.updateKnowledge(json);
        }

    }

    @Override
    public RobotInfo info(Long id) throws BaseError {
        RobotInfo robotInfo = robotInfoMapper.selectByPrimaryKey(id);

        return robotInfo;
    }

    @Override
    public List<Category> categoriesByRobot(Long id) throws Exception {
        RobotInfo robotInfo = robotInfoMapper.selectByPrimaryKey(id);
        //调用阿里机器人，获取机器人详情
        DescribeBot describeBot = botService.describe(robotInfo.getInstanceId());
        return describeBot.getCategories();
    }

    /**
     * 删除机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) throws Exception {
        RobotInfo robotInfo = robotInfoMapper.selectByPrimaryKey(id);
        robotInfoMapper.deleteByPrimaryKey(id);

        //删除关联知识类目
        RobotCategoryRelationExample example = new RobotCategoryRelationExample();
        example.createCriteria().andRobotIdEqualTo(id);
        RobotCategoryRelation robotCategoryRelation = robotCategoryRelationMapper.selectOneByExampleSelective(example,
                RobotCategoryRelation.Column.id);
        robotCategoryRelationMapper.deleteByPrimaryKey(robotCategoryRelation.getId());
        //try {
        //    //调用阿里机器人，删除机器人
        //    botService.deleteBot(robotInfo.getInstanceId());
        //}catch (Exception e){
        //    log.error("delete Bot error ",e);
        //}

    }

    /**
     * 机器⼈关联知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void bindCategorys(Long id, Long[] categoryIds) throws Exception {
        //如果绑定过知识类目，不允许再次绑定
        RobotCategoryRelationExample example = new RobotCategoryRelationExample();
        example.createCriteria().andRobotIdEqualTo(id);
        RobotCategoryRelation robotCategoryRelation = robotCategoryRelationMapper.selectOneByExampleSelective(example,
                RobotCategoryRelation.Column.robotId);
        if(null == robotCategoryRelation){
            RobotInfo robotInfo = robotInfoMapper.selectByPrimaryKey(id);
            List<Long> categoryIdList = new ArrayList<>();
            for (Long categoryId : categoryIds){
                RobotCategoryRelation relation = new RobotCategoryRelation();
                relation.setCategoryId(categoryId.intValue());
                relation.setRobotId(id);
                relation.setCtime(new Date());
                robotCategoryRelationMapper.insertSelective(relation);
                categoryIdList.add(categoryId);
            }
            botService.linkBotCategory(robotInfo.getInstanceId(),categoryIdList);
        }



    }

    @Override
    public void bindBaseUser(Long id,int baseUserId,String baseUserName) throws BaseError {
        RobotInfo tempRobotInfo = new RobotInfo();
        tempRobotInfo.setId(id);
        tempRobotInfo.setBaseUserId(baseUserId);
        tempRobotInfo.setBaseUserName(baseUserName);
        //生成h5_url
        //robotInfo.setH5Url();
        //有效期 默认1年
        tempRobotInfo.setIndate(DateUtil.getAfterDayDate(new Date(),robotConfig.getInDate()));
        tempRobotInfo.setUtime(new Date());
        tempRobotInfo.setBtime(new Date());
        int i = robotInfoMapper.updateByPrimaryKeySelective(tempRobotInfo);
        if(0==i) throw frobotErrors.Errors("robotinfo bind user fail");


    }

    /**
     * 绑定机器人
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void bindRobot(String instanceId, Integer type) throws Exception {
        //判断机器人是否已存在，存在跳过
            RobotInfoExample example = new RobotInfoExample();
            example.createCriteria().andInstanceIdEqualTo(instanceId);
            RobotInfo tempRobotInfo = robotInfoMapper.selectOneByExampleSelective(example,RobotInfo.Column.id);
            //如果为空，则添加机器人
            if(null == tempRobotInfo){
                //获取机器人详情   返回如果为空 跳出
                DescribeBot describe = robotInfoService.describe(instanceId);
                if(null  == describe) return;
                //创建机器人
                long id = robotId.generateId(robotIdConfig.getTypeCourse(), robotIdConfig.getDc());
                RobotInfo r = new RobotInfo();
                r.setId(id);
                r.setCtime(new Date());
                r.setUtime(new Date());
                r.setType(type.byteValue());
                r.setName(describe.getName());
                r.setInstanceId(instanceId);
                r.setAvatar(describe.getAvatar());
                r.setLanguageCode(describe.getLanguageCode());
                r.setTimeZone(describe.getTimezone());
                robotInfoMapper.insertSelective(r);
                //绑定知识类目
                List<Category> categoryList = describe.getCategories();
                if(null != categoryList){
                    for(Category category : categoryList){
                        RobotCategoryRelation robotCategoryRelation = new RobotCategoryRelation();
                        robotCategoryRelation.setCategoryId(category.getCategoryId().intValue());
                        robotCategoryRelation.setRobotId(id);
                        robotCategoryRelation.setCtime(new Date());
                        robotCategoryRelationMapper.insertSelective(robotCategoryRelation);
                    }
                }

            }
    }

    /**
     * 获取机器⼈详情
     * 开启单独事务，不受其他事务影响
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public DescribeBot describe(String instanceId){
        try {
            DescribeBot describeBot = botService.describe(instanceId);
            return describeBot;
        }catch (Exception e){
            log.error("robot describe instanceId is {} , error {} ",instanceId,e);
        }
       return null;
    }
}
