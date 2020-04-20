package f.s.frobot.controller;

import f.s.frobot.response.RobotChatResponse;
import f.s.frobot.service.RobotService;
import f.s.frobot.vo.RobotChatVO;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机器人控制器
 * @author lijiafu
 * @date 2019/12/31 11:06
 */
@RestController
@RequestMapping("/robot")
public class RobotController extends BaseController {
    @Autowired
    private RobotService robotService;
    /**
     * 聊天问答
     * @author lijiafu
     * @date 2019/12/31 14:37
     */
    @PostMapping("/chat")
    public ResultModel chat(RobotChatVO robotChatVO) throws BaseError {
        RobotChatResponse response = robotService.chat(robotChatVO);
        return ResultModel.success(response);
    }

    /**
     * 热点问题
     * @author lijiafu
     * @date 2019/12/31 14:37
     */
    @GetMapping("/hot")
    public ResultModel hot(String instanceId) throws BaseError {
        Map<String,String> map = robotService.hot(instanceId);
        return ResultModel.success(map);
    }

    /**
     * 推荐问题
     * @author lijiafu
     * @date 2019/12/31 14:37
     */
    @GetMapping("/recommend")
    public ResultModel recommend(String instanceId) throws BaseError {
        Map<String,String> map = robotService.recommend(instanceId);
        return ResultModel.success(map);
    }
}
