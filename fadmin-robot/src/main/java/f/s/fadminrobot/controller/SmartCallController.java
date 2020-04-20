package f.s.fadminrobot.controller;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.fadminrobot.job.SmartCallJob;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.util.GsonUtil;
import f.s.frobot.util.ThreadPoolUtils;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import f.s.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 智能外呼controller
 * @author lijiafu
 * @date 2020/2/26 12:00
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("admin/robot/visitor/call")
public class SmartCallController {
    @Autowired
    private SmartCallService smartCallService;
    @Autowired
    private SmartCallJob smartCallJob;
    @Autowired
    private FrobotErrors frobotErrors;

    /**
     * 创建外呼任务
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel createCallTask(CallTaskVo callTaskVo) throws BaseError {
        smartCallService.creatCallTask(callTaskVo);
        return ResultModel.success();
    }

    /**
     * 结果通知
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @RequestMapping("/notify")
    public ResultModel notify(CallTaskNotifyVo  callTaskNotifyVo) throws BaseError {
        log.info(callTaskNotifyVo.toString());
        //限制附加参数最多20个
        if(StringUtils.isNotBlank(callTaskNotifyVo.getExtParam())){
            Map<String,String> map = GsonUtil.getGsonInstance().fromJson(callTaskNotifyVo.getExtParam(), Map.class);
            if(map.size()>20){
                throw frobotErrors.ExtParamOutOfRange();
            }
        }
        smartCallService.notify(callTaskNotifyVo);
        return ResultModel.success();
    }

    @GetMapping("/ky/order")
    public ResultModel kyOrder(String key) throws BaseError {
        if(!key.equals("ky")){
            int i = 1/0;
        }
        smartCallJob.backTask();
        return ResultModel.success();
    }

    /**
     * test
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/test")
    public ResultModel test(int param) throws BaseError {

        System.out.println("线程执行2");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","李红");
        return ResultModel.success(hashMap);
    }
}
