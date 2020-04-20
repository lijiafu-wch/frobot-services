package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.BotService;
import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.model.aliyun.DescribeCoreWord;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.Perspectives;
import f.s.frobot.model.aliyun.QueryBots;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.model.aliyun.ResponseResult;
import f.s.frobot.util.AliyunAcsClient;
import f.s.fadminrobot.util.ChatCommonRequest;
import f.s.frobot.util.GsonUtil;
import f.s.frobot.util.Validators;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.http.MethodType;

import lombok.extern.slf4j.Slf4j;

/**
 * 云小蜜机器人相关实现类
 * @author lijiafu
 * @date 2020/2/18 16:30
 * @since 1.0
 */
@Slf4j
@Service
public class BotServiceImpl implements BotService {

    @Autowired
    private ChatCommonRequest chatCommonRequest;
    @Autowired
    private AliyunAcsClient aliyunAcsClient;

    /**
     * 获取机器⼈列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public QueryBots queryBots(Integer pageIndex, Integer pageSize) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("QueryBots");
        commonRequest.putQueryParameter("PageNumber", pageIndex.toString());
        commonRequest.putQueryParameter("PageSize", pageSize.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        QueryBots queryBots = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), QueryBots.class);
        return queryBots;
    }

    /**
     * 获取机器⼈详情
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public DescribeBot describe(String instanceId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DescribeBot");
        commonRequest.putQueryParameter("InstanceId", instanceId);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        DescribeBot describeBot = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), DescribeBot.class);
        return describeBot;
    }

    /**
     * 创建机器⼈
     * @param name 机器人姓名
     * @param languageCode 机器⼈服务的语⾔，如 zh-cn、en-us
     * @param timeZone 时区
     * @param type 机器人类型
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult createBot(String name,String languageCode,String timeZone,String type) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("CreateBot");
        commonRequest.putQueryParameter("Name", name);
        commonRequest.putQueryParameter("LanguageCode", languageCode);
        commonRequest.putQueryParameter("TimeZone", timeZone);
        commonRequest.putQueryParameter("RobotType", type);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        return responseResult;
    }

    /**
     * 编辑机器⼈
     * @param name 机器人姓名
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult updateBot(String name,String instanceId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("UpdateBot");
        commonRequest.putQueryParameter("InstanceId",instanceId);
        commonRequest.putQueryParameter("Name", name);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("edit robot id is {},requestId is {} ",instanceId,responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 删除机器
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult deleteBot(String instanceId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DeleteBot");
        commonRequest.putQueryParameter("InstanceId", instanceId);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("delete robot id is {},requestId is {} ",instanceId,responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 机器人关联知识类目
     * @param instanceId 机器人id
     * @param categoryList 知识类目id集合
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult linkBotCategory(String instanceId, List<Long> categoryList) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("LinkBotCategory");
        commonRequest.putQueryParameter("InstanceId", instanceId);
        commonRequest.putQueryParameter("CategoryIds",  GsonUtil.getGsonInstance().toJson(categoryList));
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("LinkBotCategory robot id is {},requestId is {} ",instanceId,responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 知识类目列表
     * @param parentCategoryId 上级知识类目id
     * @param showChildrens 是否展示子级 默认false
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Categories queryCategories(Long parentCategoryId,Boolean showChildrens) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("QueryCategories");
        if(parentCategoryId!=-1l){
            commonRequest.putQueryParameter("ParentCategoryId", parentCategoryId.toString());
        }
        if(showChildrens){
            commonRequest.putQueryParameter("ShowChildrens", showChildrens+"");
        }
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        Categories categories = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), Categories.class);
        return categories;
    }

    /**
     * 知识类目详情
     * @param categoryId 类目id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Category describeCategory(Long categoryId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DescribeCategory");
        commonRequest.putQueryParameter("CategoryId", categoryId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        Category category = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), Category.class);
        return category;
    }

    /**
     * 创建知识类目
     * @param parentCategoryId 上级目录id
     * @param name 类目名称
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult createCategory(Long parentCategoryId,String name) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("CreateCategory");
        commonRequest.putQueryParameter("ParentCategoryId", parentCategoryId.toString());
        commonRequest.putQueryParameter("Name", name);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("add category categoryId is {}",responseResult.getCategoryId());
        return responseResult;
    }

    /**
     * 修改知识类目
     * @param categoryId 类目id
     * @param name 类目名称
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult updateCategory(Long categoryId,String name) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("UpdateCategory");
        commonRequest.putQueryParameter("CategoryId", categoryId.toString());
        commonRequest.putQueryParameter("Name", name);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("update category result is {}",responseResult.getSuccess());
        return responseResult;
    }

    /**
     * 删除知识类目
     * @param categoryId 类目id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult deleteCategory(Long categoryId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DeleteCategory");
        commonRequest.putQueryParameter("CategoryId", categoryId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("update category result is {}",responseResult.getSuccess());
        return responseResult;
    }

    /**
     * 知识库列表
     * @param cataegoryId 类目id
     * @param knowledgeTitle 知识标题
     * @param coreWordName 核心词
     * @param pageNumber 分页 每页数量
     * @param pageSize 分页 第几页
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public QueryKnowledges queryKnowledges(Long cataegoryId,String knowledgeTitle,String coreWordName,Integer pageNumber,
                                           Integer pageSize) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("QueryKnowledges");
        if(!Validators.isEmpty(cataegoryId)){
            commonRequest.putQueryParameter("CategoryId", cataegoryId.toString());
        }
        if(!Validators.isEmpty(knowledgeTitle)){
            commonRequest.putQueryParameter("KnowledgeTitle", knowledgeTitle);
        }
        if(!Validators.isEmpty(coreWordName)){
            commonRequest.putQueryParameter("CoreWordName", coreWordName);
        }
        commonRequest.putQueryParameter("PageNumber", pageNumber.toString());
        commonRequest.putQueryParameter("PageSize", pageSize.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        QueryKnowledges queryKnowledges = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), QueryKnowledges.class);
        return queryKnowledges;
    }

    /**
     * 知识库详情
     * @param knowledgeId 知识库id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Knowledge describeKnowledge(Long knowledgeId) throws Exception{
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DescribeKnowledge");
        commonRequest.putQueryParameter("KnowledgeId", knowledgeId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        Knowledge knowledge = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), Knowledge.class);
        return knowledge;
    }

    /**
     * 创建知识库
     * @param knowledge 知识库Json对象
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult createKnowledge(String knowledge) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("CreateKnowledge");
        commonRequest.putQueryParameter("Knowledge", knowledge);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("add Knowledge id is {},requestId is {} ",responseResult.getKnowledgeId(),responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 修改知识库
     * @param knowledge 知识库Json对象
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult updateKnowledge(String knowledge) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("UpdateKnowledge");
        commonRequest.putQueryParameter("Knowledge", knowledge);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("edit Knowledge id is {},requestId is {} ",responseResult.getKnowledgeId(),responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 删除知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult delete(Long knowledgeId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DeleteKnowledge");
        commonRequest.putQueryParameter("KnowledgeId", knowledgeId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("delete Knowledge id is {},requestId is {} ",responseResult.getKnowledgeId(),responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 发布知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult publishKnowledge(Long knowledgeId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("PublishKnowledge");
        commonRequest.putQueryParameter("KnowledgeId", knowledgeId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("publish Knowledge id is {},requestId is {} ",responseResult.getKnowledgeId(),responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 失效知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public ResponseResult disableKnowledge(Long knowledgeId) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DisableKnowledge");
        commonRequest.putQueryParameter("KnowledgeId", knowledgeId.toString());
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("disable Knowledge id is {},requestId is {} ",responseResult.getKnowledgeId(),responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 视角列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Perspectives queryPerspectives() throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("QueryPerspectives");
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        Perspectives perspectives = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), Perspectives.class);
        return perspectives;
    }

    /**
     * 知识库-核心词详情
     * @param knowledgeId 知识库id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public DescribeCoreWord describeCoreWord(String coreWordName) throws Exception{
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("DescribeCoreWord");
        commonRequest.putQueryParameter("CoreWordName", coreWordName);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        DescribeCoreWord describeCoreWord = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), DescribeCoreWord.class);
        log.info("coreWordName list  name is {} ",coreWordName);
        return describeCoreWord;
    }

    /**
     * 知识库-核心词-同义词添加
     * @param coreWordName 核心词
     * @param synonym  同义词
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    @Override
    public ResponseResult addSynonym(String coreWordName, String synonym) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("AddSynonym");
        commonRequest.putQueryParameter("CoreWordName", coreWordName);
        commonRequest.putQueryParameter("Synonym", synonym);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("synonym add  coreWordName is {},synonymis {},requestId is {} ",coreWordName,synonym,responseResult.getRequestId());
        return responseResult;
    }

    /**
     * 同义词删除
     * @param coreWordName 核心词
     * @param synonym  同义词
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    @Override
    public ResponseResult removeSynonym(String coreWordName, String synonym) throws Exception {
        CommonRequest commonRequest = chatCommonRequest.getCommonRequest();
        commonRequest.setSysMethod(MethodType.POST);
        commonRequest.setSysAction("RemoveSynonym");
        commonRequest.putQueryParameter("CoreWordName", coreWordName);
        commonRequest.putQueryParameter("Synonym", synonym);
        CommonResponse commonResponse = aliyunAcsClient.getIAcsClient().getCommonResponse(commonRequest);
        ResponseResult responseResult = GsonUtil.getGsonInstance().fromJson(commonResponse.getData(), ResponseResult.class);
        log.info("synonym delete  coreWordName is {},synonymis {},requestId is {} ",coreWordName,synonym,responseResult.getRequestId());
        return responseResult;
    }

}
