package f.s.fadminrobot.controller;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.fadminrobot.service.CallApiService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.util.GsonUtil;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import f.s.utils.StringUtils;
import f.s.utils.encryption.RSAUtils;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 智能外呼对外api
 * header中需要传参 商户标识
 * @author lijiafu
 * @date 2020/4/9 10:09
 * @since 1.0
 */
@RestController
@RequestMapping("api/call/task")
@Slf4j
public class CallApiNotifyController {

    @Autowired
    private CallApiService callApiService;
    @Autowired
    private FrobotErrors frobotErrors;

    /**
     * 结果通知
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @RequestMapping("/notify")
    public ResultModel notify(CallTaskNotifyVo callTaskNotifyVo) throws BaseError {
        log.info(callTaskNotifyVo.toString());
        //限制附加参数最多20个
        if(StringUtils.isNotBlank(callTaskNotifyVo.getExtParam())){
            Map<String,String> map = GsonUtil.getGsonInstance().fromJson(callTaskNotifyVo.getExtParam(), Map.class);
            if(map.size()>20){
                throw frobotErrors.ExtParamOutOfRange();
            }
        }
        callApiService.notify(callTaskNotifyVo);
        return ResultModel.success();
    }

}
