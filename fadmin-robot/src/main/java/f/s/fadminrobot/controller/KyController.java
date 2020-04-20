package f.s.fadminrobot.controller;

import f.s.fadminrobot.remote.KyRequest;
import f.s.fadminrobot.service.CallKyOrderService;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.model.CallKyOrder;
import f.s.frobot.model.CallTask;
import f.s.frobot.util.ThreadPoolUtils;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import f.s.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 智能外呼controller
 * @author lijiafu
 * @date 2020/2/26 12:00
 * @since 1.0
 */
@RestController
@RequestMapping("admin/robot/visitor/ky")
public class KyController {
    @Autowired
    private CallTaskService callTaskService;
    @Autowired
    private CallKyOrderService callKyOrderService;

    /**
     * 获取会员卡信息
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/vip")
    public ResultModel getVipDes(Integer taskId) throws BaseError {
        CallTask callTask = callTaskService.findById(taskId);
        CallKyOrder callKyOrder = callKyOrderService.findByOrderId(Integer.parseInt(callTask.getRequestid()));
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isBlank(callKyOrder.getMemberType())){
            map.put("vipName",KyRequest.vipNameMap.get("A"));
            map.put("delayTime",KyRequest.vipTimeMap.get("A"));
        }else{
            map.put("vipName",KyRequest.vipNameMap.get(callKyOrder.getMemberType()));
            map.put("delayTime",KyRequest.vipTimeMap.get(callKyOrder.getMemberType()));
        }

        return ResultModel.success(map);
    }

}
