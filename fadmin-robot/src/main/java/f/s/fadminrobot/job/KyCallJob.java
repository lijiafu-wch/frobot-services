package f.s.fadminrobot.job;

import f.s.fadminrobot.service.CallThirdConfigService;
import f.s.fadminrobot.third.KyRequest;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.ext.KyBackOrder;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.model.CallTask;
import f.s.frobot.util.AliyunAcsClient;
import f.s.frobot.util.GsonUtil;
import f.s.jerror.BaseError;
import f.s.utils.DateUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsRequest;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 开元酒店智能外呼定时任务
 * @author lijiafu
 * @date 2020/3/30 23:14
 * @since 1.0
 */
@Slf4j
@Component
public class KyCallJob {

    @Autowired
    private CallTaskService callTaskService;
    @Autowired
    private AliyunAcsClient aliyunAcsClient;
    @Autowired
    private KyRequest kyRequest;
    @Autowired
    private SmartCallService smartCallService;
    @Autowired
    private CallThirdConfigService callThirdConfig;
    /**
     * 开元崔退任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    @Scheduled(cron = "0 0 12 * * ?")//中午12点执行
    public void backTask() {
        // 调用开元接口
        List<KyBackOrder> orderList = kyRequest.backOrder();
        log.info("定时获取开元崔退订单-------------start-----，数量 : {}",orderList.size());
        //商户id
        Integer merchantId = 30;
        //外呼实例id
        String callTypeId = callThirdConfig.getValue(merchantId,"ky_request","callTypeId");
        for(KyBackOrder kyBackOrder : orderList){
            CallTask callTask = callTaskService.getTaskByRequestId(kyBackOrder.getId() + "");
            if(null == callTask){
                //会员类型
                String memberType = callThirdConfig.getValue(merchantId,"ky_vipName",kyBackOrder.getMemberType());
                //延退时间
                String dlayTime = callThirdConfig.getValue(merchantId,"ky_vipTime",kyBackOrder.getMemberType());

                //创建执行任务
                CallTaskVo callTaskVo = new CallTaskVo();
                callTaskVo.setMerchantId(merchantId);//商家id
                callTaskVo.setCallTypeId(Long.parseLong(callTypeId));//外呼实例id
                callTaskVo.setPhoneNum(Integer.parseInt(kyBackOrder.getRmno()));//被叫号码
                callTaskVo.setRequestId(kyBackOrder.getId()+"");//请求id
                callTaskVo.setRootNum(kyBackOrder.getRmno());//房间号
                Map<String,String> map  = new HashMap<>();
                map.put("memberType",memberType);
                map.put("dlayTime",dlayTime);
                map.put("roomPhone",kyBackOrder.getRmno());
                callTaskVo.setExtParam(GsonUtil.getGsonInstance().toJson(map));
                try {
                    smartCallService.creatCallTask(callTaskVo);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("add task error ,requestId : {},error : {}",kyBackOrder.getId(),e.getMessage());
                }
            }
        }
        log.info("定时获取开元崔退订单-------------end-----,数量：{}",orderList.size());
    }

    /**
     * 开元没有返回结果的任务，重新创建任务
     * 查询当天开元，没有小蜜返回结果的任务，判断状态，重新创建任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    @Scheduled(cron = "0 50 12 * * ?")//中午12点50执行
    //@Scheduled(cron = "0 */1 * * * ?")
    private void currentExcuteTask() {
        log.info("current excute task start ..................");
        List<CallTask> excuteList = callTaskService.getCurrentExcuteList(1001);
        if(CollectionUtils.isEmpty(excuteList)){
            return ;
        }
        Date date = new Date();
        for(CallTask task : excuteList){
            QueryJobsRequest request = new QueryJobsRequest();
            request.setInstanceId(task.getInstanceId());
            request.setJobGroupId(task.getGroupId());
            request.setStartTime(task.getExcuteTime().getTime());
            request.setEndTime(date.getTime());
            request.setTimeAlignment("start");
            request.setPhoneNumber(task.getPhoneNum()+"");
            request.setPageNumber(1);
            request.setPageSize(1);
            try {
                QueryJobsResponse response  = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
                if(response.getHttpStatusCode().equals(200)) {
                    List list = response.getJobs().getList();
                    if(!CollectionUtils.isEmpty(list)){
                        QueryJobsResponse.Jobs.Job job = (QueryJobsResponse.Jobs.Job) list.get(0);
                        String status  = job.getStatus();
                        log.info("return status : {},taskId : {}",status,task.getId());
                        //修改 callTask的状态为已完成
                        CallTask tempTask = new CallTask();
                        tempTask.setId(task.getId());
                        if("Succeeded".equals(job.getStatus())){
                            tempTask.setStatus(Byte.parseByte("2"));
                            tempTask.setCallResult(status);//外呼返回结果
                        }else{  //重新执行
                            tempTask.setStatus(Byte.parseByte("0"));
                            tempTask.setExecuteNum(Byte.parseByte("1"));//重新执行的次数
                            tempTask.setExcuteTime(DateUtil.parseToDateTime(DateUtil.getDay(date)+" 13:00:00"));
                        }
                        tempTask.setUtime(date);
                        callTaskService.updateCallTask(tempTask);
                    }
                }
            } catch (ClientException e) {
                log.error("QueryJobs ErrCode: {},ErrMsg {},RequestId {}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            }
        }
        log.info("current excute task end ..................");
    }
}
