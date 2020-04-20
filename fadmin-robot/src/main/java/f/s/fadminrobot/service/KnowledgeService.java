package f.s.fadminrobot.service;

import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.vo.CategoryVo;
import f.s.frobot.vo.KnowledgeVo;
import f.s.frobot.vo.PageVo;

/**
 *  知识库接口
 * @author lijiafu
 * @date 2020/2/18 16:27
 * @since 1.0
 */
public interface KnowledgeService {

    /**
     * 知识库列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    QueryKnowledges list(KnowledgeVo knowledgeVo, PageVo pageVo) throws Exception;

    /**
     * 知识库详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Knowledge info(KnowledgeVo knowledgeVo) throws Exception;
    /**
     * 创建知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void add(String knowledge) throws Exception;
    /**
     * 修改知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void edit(String knowledge) throws Exception;
    /**
     * 删除知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void delete(KnowledgeVo knowledgeVo) throws Exception;
    /**
     * 发布知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void publish(KnowledgeVo knowledgeVo) throws Exception;
    /**
     * 失效知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void disable(KnowledgeVo knowledgeVo) throws Exception;

}
