package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.PerspectiveService;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.model.aliyun.Perspectives;
import f.s.frobot.model.aliyun.QueryBots;
import f.s.utils.ResultModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机器人controller
 * @author lijiafu
 * @date 2019/12/31 11:06
 */
@RestController
@RequestMapping("/admin/perspective")
public class PerspectiveController extends BaseController {
    @Autowired
    private PerspectiveService perspectiveService;

    /**
     * 视角列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/list")
    public ResultModel list() throws Exception {
        Perspectives perspectives = perspectiveService.List();
        return ResultModel.success(perspectives.getPerspectives());
    }
}
