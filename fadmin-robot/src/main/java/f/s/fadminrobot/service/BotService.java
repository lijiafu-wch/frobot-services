package f.s.fadminrobot.service;

import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.model.aliyun.DescribeCoreWord;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.Perspectives;
import f.s.frobot.model.aliyun.QueryBots;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.model.aliyun.ResponseResult;
import java.util.List;

/**
 *  机器人信息接口
 * @author lijiafu
 * @date 2020/2/18 16:27
 * @since 1.0
 */
public interface BotService {

    /**
     * 获取机器⼈列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    QueryBots queryBots(Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 获取机器⼈详情
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    DescribeBot describe(String instanceId) throws Exception;

    /**
     * 创建机器⼈
     * @param name 机器人姓名
     * @param languageCode 机器⼈服务的语⾔，如 zh-cn、en-us
     * @param timeZone 时区
     * @param type 机器人类型
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult createBot(String name, String languageCode, String timeZone, String type) throws Exception;

    /**
     * 编辑机器⼈
     * @param name 机器人姓名
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult updateBot(String name,String instanceId) throws Exception;

    /**
     * 删除机器
     * @param instanceId 机器人id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult deleteBot(String instanceId) throws Exception;

    /**
     * 机器人关联知识类目
     * @param instanceId 机器人id
     * @param categoryList 知识类目id集合
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult linkBotCategory(String instanceId, List<Long> categoryList) throws Exception;


    /**
     * 知识类目列表
     * @param parentCategoryId 上级知识类目id
     * @param showChildrens 是否展示子级 默认false
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Categories queryCategories(Long parentCategoryId, Boolean showChildrens) throws Exception;

    /**
     * 知识类目详情
     * @param categoryId 类目id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Category describeCategory(Long categoryId) throws Exception;

    /**
     * 创建知识类目
     * @param parentCategoryId 上级目录id
     * @param name 类目名称
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult createCategory(Long parentCategoryId,String name) throws Exception;

    /**
     * 修改知识类目
     * @param categoryId 类目id
     * @param name 类目名称
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult updateCategory(Long categoryId,String name) throws Exception;

    /**
     * 删除知识类目
     * @param categoryId 类目id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult deleteCategory(Long categoryId) throws Exception;

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
    QueryKnowledges queryKnowledges(Long cataegoryId, String knowledgeTitle, String coreWordName, Integer pageNumber,
                                           Integer pageSize) throws Exception;

    /**
     * 知识库详情
     * @param knowledgeId 知识库id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Knowledge describeKnowledge(Long knowledgeId) throws Exception;

    /**
     * 创建知识库
     * @param knowledge 知识库Json对象
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult createKnowledge(String knowledge) throws Exception;

    /**
     * 修改知识库
     * @param knowledge 知识库Json对象
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult updateKnowledge(String knowledge) throws Exception;

    /**
     * 删除知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult delete(Long knowledgeId) throws Exception;

    /**
     * 发布知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult publishKnowledge(Long knowledgeId) throws Exception;

    /**
     * 失效知识库
     * @param knowledgeId  知识id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    ResponseResult disableKnowledge(Long knowledgeId) throws Exception;

    /**
     * 视角列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Perspectives queryPerspectives() throws Exception;

    /**
     * 知识库-核心词详情
     * @param knowledgeId 知识库id
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
     DescribeCoreWord describeCoreWord(String coreWordName) throws Exception;

    /**
     * 知识库-核心词-同义词添加
     * @param coreWordName 核心词
     * @param synonym  同义词
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
     ResponseResult addSynonym(String coreWordName, String synonym) throws Exception;

    /**
     * 同义词删除
     * @param coreWordName 核心词
     * @param synonym  同义词
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    ResponseResult removeSynonym(String coreWordName, String synonym) throws Exception;
}
