package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.SynonymService;
import f.s.frobot.model.aliyun.QueryKnowledges;
import f.s.frobot.vo.KnowledgeVo;
import f.s.frobot.vo.PageVo;
import f.s.utils.ResultModel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 同义词
 * @author lijiafu
 * @date 2020/2/28 15:33
 * @since 1.0
 */
@RestController
@RequestMapping("/admin/robot/synonym")
public class SynonymController {
    @Autowired
    private SynonymService synonymService;

    /**
     * 同义词列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/list")
    public ResultModel list(String coreWordName) throws Exception{
        List<String> list = synonymService.list(coreWordName);
        return ResultModel.success(list);
    }

    /**
     * 同义词添加
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel add(String coreWordName,String synonym)  throws Exception{
        synonymService.add(coreWordName, synonym);
        return ResultModel.success();
    }
    /**
     * 同义词删除
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/delete")
    public ResultModel delete(String coreWordName,String synonym) throws Exception{
        synonymService.delete(coreWordName, synonym);
        return ResultModel.success();
    }

}
