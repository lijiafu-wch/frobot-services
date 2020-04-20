package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.BotService;
import f.s.frobot.model.aliyun.DescribeBot;
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
@RequestMapping("/admin/bot")
public class BotController extends BaseController {
    @Autowired
    private BotService botService;

    /**
     * 获取机器⼈列表
     * @author lijiafu
     * @param pageNumber 页数 默认1
     * @param pageSize 条数 默认10
     * @date 2020/2/18 16:30
     */
    @GetMapping("/query")
    public ResultModel queryBots(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) throws Exception {
        QueryBots queryBots = botService.queryBots(pageNumber, pageSize);
        return ResultModel.success(queryBots.getBots());
    }

    /**
     * 获取机器⼈详情
     * @author lijiafu
     * @param instanceId 机器⼈ID
     * @date 2020/2/18 16:30
     */
    @GetMapping("/describe")
    public ResultModel describe(String instanceId) throws Exception {
        DescribeBot describeBot = botService.describe(instanceId);
        return ResultModel.success(describeBot);
    }
}
