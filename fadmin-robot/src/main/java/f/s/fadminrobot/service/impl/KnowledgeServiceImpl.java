package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.KnowledgeService;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.vo.KnowledgeVo;
import f.s.frobot.vo.PageVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *  知识库实现类
 * @author lijiafu
 * @date 2020/2/26 16:31
 * @since 1.0
 */
@Service
@Slf4j
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private BotService botService;

    /**
     * 知识库列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public QueryKnowledges list(KnowledgeVo knowledgeVo, PageVo pageVo) throws Exception {
        QueryKnowledges queryKnowledges = botService.queryKnowledges(knowledgeVo.getCategoryId(),knowledgeVo.getKnowledgeTitle(),
                knowledgeVo.getCoreWordName(),pageVo.getPageIndex(),
                pageVo.getPageSize());
        return queryKnowledges;
    }

    /**
     * 知识库详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Knowledge info(KnowledgeVo knowledgeVo) throws Exception{
        Knowledge knowledge = botService.describeKnowledge(knowledgeVo.getKnowledgeId());
        return knowledge;
    }

    /**
     * 创建知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void add(String knowledge) throws Exception {
        botService.createKnowledge(knowledge);
    }

    /**
     * 修改知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void edit(String knowledge) throws Exception {
        botService.updateKnowledge(knowledge);
    }

    /**
     * 删除知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void delete(KnowledgeVo knowledgeVo) throws Exception {
       botService.describeKnowledge(knowledgeVo.getKnowledgeId());

    }
    /**
     * 发布知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void publish(KnowledgeVo knowledgeVo) throws Exception {
        botService.publishKnowledge(knowledgeVo.getKnowledgeId());
    }

    /**
     * 失效知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void disable(KnowledgeVo knowledgeVo) throws Exception {
        botService.disableKnowledge(knowledgeVo.getKnowledgeId());
    }
}
