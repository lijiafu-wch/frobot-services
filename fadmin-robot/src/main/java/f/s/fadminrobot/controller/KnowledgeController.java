package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.KnowledgeService;
import f.s.fadminrobot.vo.KonwledgeJsonVo;
import f.s.frobot.model.aliyun.Knowledge;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.util.GsonUtil;
import f.s.frobot.vo.KnowledgeVo;
import f.s.frobot.vo.PageVo;
import f.s.utils.ResultModel;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 知识类目controller
 * @author lijiafu
 * @date 2020/2/26 12:00
 * @since 1.0
 */
@RestController
@RequestMapping("admin/robot/knowledge")
@Slf4j
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    /**
     * 知识列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/list")
    public ResultModel list(KnowledgeVo knowledgeVo, PageVo pageVo) throws Exception{
        QueryKnowledges queryKnowledges = knowledgeService.list(knowledgeVo,pageVo);
        pageVo.setTotal(queryKnowledges.getTotalCount());
        pageVo.setTotalPages(queryKnowledges.getTotalCount()/ pageVo.getPageSize());
        pageVo.setRows(queryKnowledges.getKnowledges());
        return ResultModel.success(pageVo);
    }

    /**
     * 创建知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel add(String knowledge) throws Exception{
       knowledge  = URLDecoder.decode(knowledge, "utf-8");
        log.info("add knowledge is {}",knowledge);
        knowledgeService.add(knowledge);
        return ResultModel.success();
    }


    /**
     * 修改知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/edit")
    public ResultModel edit(String knowledge) throws Exception{
        knowledge = URLDecoder.decode(knowledge, "utf-8");
        log.info("edit knowledge is {}",knowledge);
        knowledgeService.edit(knowledge);
        return ResultModel.success();
    }


    /**
     * 知识库详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/info")
    public ResultModel info(KnowledgeVo knowledgeVo) throws Exception{
        Knowledge knowledge = knowledgeService.info(knowledgeVo);
        return ResultModel.success(knowledge);
    }

    /**
     * 删除知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/delete")
    public ResultModel delete(KnowledgeVo knowledgeVo) throws Exception{
        knowledgeService.delete(knowledgeVo);
        return ResultModel.success();
    }

    /**
     * 发布知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/publish")
    public ResultModel publish(KnowledgeVo knowledgeVo) throws Exception{
        knowledgeService.publish(knowledgeVo);
        return ResultModel.success();
    }
    /**
     * 失效知识库
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/disable")
    public ResultModel disable(KnowledgeVo knowledgeVo) throws Exception{
        knowledgeService.disable(knowledgeVo);
        return ResultModel.success();
    }
}
