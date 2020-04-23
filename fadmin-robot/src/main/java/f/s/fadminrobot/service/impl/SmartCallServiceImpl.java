package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.remote.KyRequest;
import f.s.fadminrobot.service.CallInstanceService;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.model.CallInstance;
import f.s.frobot.model.CallTask;
import f.s.jerror.BaseError;
import f.s.utils.StringUtils;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 智能外呼
 * @author lijiafu
 * @date 2020/3/29 21:25
 * @since 1.0
 */
@Slf4j
@Service
public class SmartCallServiceImpl implements SmartCallService {
    @Autowired
    private CallTaskService callTaskService;
    @Autowired
    private CallInstanceService callInstanceService;
    @Autowired
    private KyRequest kyRequest;

    /**
     * 创建任务
     * @author lijiafu
     * @date 2020/4/2 0:22
     */
    @Override
    public void creatCallTask(CallTaskVo callTaskVo) throws BaseError {
        //根据外呼实例
        CallInstance callInstance = callInstanceService.findById(callTaskVo.getCallTypeId());
        //实例id
        callTaskVo.setInstanceId(callInstance.getInstanceId());
        //组id
        callTaskVo.setGroupId(callInstance.getGroupId());
        //主叫号码
        callTaskVo.setCallingNum(callInstance.getCallingNum());
        CallTask callTask = assCallTask(callInstance,callTaskVo);
        //创建执行任务
        callTaskService.add(callTask);
    }

    /**
     * 复制一个任务
     * @author lijiafu
     * @date 2020/4/2 0:22
     */
    @Override
    public void copyCallTask(CallTask callTask) throws BaseError {
        //创建执行任务
        callTaskService.add(callTask);
    }


    /**
     * 结果通知
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void notify(CallTaskNotifyVo callTaskNotifyVo) throws BaseError {
        log.info("结果通知，taskId : {}",callTaskNotifyVo.getTaskId()    );
        //根据任务id，查找任务
        CallTask callTask = callTaskService.findById(callTaskNotifyVo.getTaskId());
        //TODO 添加日志

        String notifyResult = null;//第三方返回结果
        //通知第三方 ，查询第三方配置 处理错误 和网络超时
        if(null != callTask && StringUtils.isNotBlank(callTask.getRequestid())){
            try {
                notifyResult = kyRequest.kyNotify(callTask.getRequestid(), callTaskNotifyVo.getResult());
            } catch (Exception e) {
                log.error("request fail taskId : {},requestId :{}",callTask.getId(),callTask.getRequestid());
                e.printStackTrace();
            }
        }
        //更新任务状态
        CallTask tempTask = new CallTask();
        tempTask.setId(callTaskNotifyVo.getTaskId());
        tempTask.setStatus(Byte.parseByte("2"));//已执行
        tempTask.setResult(callTaskNotifyVo.getResult());
        tempTask.setNotifyResult(notifyResult);
        tempTask.setUtime(new Date());
        callTaskService.updateCallTask(tempTask);
        //验证是否需要进行其他通知
        if(null != callTask && callTaskNotifyVo.isIfCall()){
            //创建执行任务
            //根据外呼实例
            CallTaskVo callTaskVo = new CallTaskVo();
            CallInstance callInstance = callInstanceService.findById(callTaskNotifyVo.getCallTypeId());
            //实例id
            callTaskVo.setInstanceId(callInstance.getInstanceId());
            //组id
            callTaskVo.setGroupId(callInstance.getGroupId());
            //主叫号码
            callTaskVo.setCallingNum(callInstance.getCallingNum());
            //被叫号码 TODO  如客房服务或前台，
            callTaskVo.setPhoneNum(callInstance.getCalledNum());
            //房间号
            callTaskVo.setRootNum(callTask.getRoomNum());
            //扩展参数
            callTaskVo.setExtParam(callTaskNotifyVo.getExtParam());
            CallTask task = assCallTask(callInstance,callTaskVo);
            //创建执行任务
            callTaskService.add(task);
        }
    }


    //组装CallTask
    private CallTask assCallTask(CallInstance callInstance,CallTaskVo callTaskVo){
        Date date = new Date();
        CallTask callTask = new CallTask();
        callTask.setCallingNum(callTaskVo.getCallingNum());//主叫号码
        callTask.setPhoneNum(callTaskVo.getPhoneNum());//被叫手机号
        callTask.setRoomNum(callTaskVo.getRootNum());//房间号
        callTask.setInstanceId(callTaskVo.getInstanceId());//外呼实例，崔退
        callTask.setGroupId(callTaskVo.getGroupId());//外呼执行组id
        callTask.setRequestid(callTaskVo.getRequestId());//第三方id
        callTask.setCallInstanceId(callInstance.getId());//系统智能外呼实例id
        callTask.setBaseUserId(callTaskVo.getMerchantId());//商户id
        if(StringUtils.isNotBlank(callInstance.getStrategy())){//外呼策略
            callTask.setStrategy(callInstance.getStrategy());
        }
        if(null  == callTaskVo.getExcuteTime()){
            callTask.setExcuteTime(date);//执行时间
        }else{
            callTask.setExcuteTime(callTaskVo.getExcuteTime());//执行时间
        }
        callTask.setCtime(date);//创建时间
        callTask.setUtime(date);//修改时间
        if(StringUtils.isNotBlank(callTaskVo.getExtParam())){
            callTask.setExtParam(callTaskVo.getExtParam());
        }
        return callTask;
    }

}
