package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.util.GsonUtil;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import f.s.utils.encryption.RSAUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 智能外呼对外api
 * header中需要传参 商户标识
 * @author lijiafu
 * @date 2020/4/9 10:09
 * @since 1.0
 */
@RestController
@RequestMapping("api/call/task")
public class CallApiController {

    @Autowired
    private SmartCallService smartCallService;
    /**
     * 创建外呼任务
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/createTask1")
    public ResultModel createTask1(@RequestHeader()Integer idNum, String encryptData) throws BaseError {
        //根据idNum找到对应的商户的密钥，对encryptData进行解密
        String priKey ="";
        CallTaskVo callTaskVo = null;
        try {
            String data = RSAUtils.decryptByPrivateKey(priKey, encryptData);
            callTaskVo = GsonUtil.getGsonInstance().fromJson(data, CallTaskVo.class);
        } catch (Exception e) {
            //抛出异常 解密失败，请检查密钥和参数是否准确
            e.printStackTrace();
        }
        callTaskVo.setMerchantId(idNum);

        smartCallService.creatCallTask(callTaskVo);
        return ResultModel.success();
    }

    /**
     * 创建外呼任务
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel createTask(String data) throws BaseError {
        //TODO aop拦截 解析密钥
        CallTaskVo callTaskVo =  GsonUtil.getGsonInstance().fromJson(data, CallTaskVo.class);
        smartCallService.creatCallTask(callTaskVo);
        return ResultModel.success();
    }

}
