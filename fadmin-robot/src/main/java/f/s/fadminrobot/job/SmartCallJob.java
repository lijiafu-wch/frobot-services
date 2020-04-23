package f.s.fadminrobot.job;

import f.s.fadminrobot.remote.KyRequest;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.ext.KyBackOrder;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.fadminrobot.vo.smartcall.Contact;
import f.s.fadminrobot.vo.smartcall.Extra;
import f.s.fadminrobot.vo.smartcall.JobsJson;
import f.s.frobot.model.CallTask;
import f.s.frobot.util.AliyunAcsClient;
import f.s.frobot.util.GsonUtil;
import f.s.jerror.BaseError;
import f.s.utils.DateUtil;
import f.s.utils.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.outboundbot.model.v20191226.AssignJobsRequest;
import com.aliyuncs.outboundbot.model.v20191226.AssignJobsResponse;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsRequest;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsResponse;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 智能外呼定时任务
 * @author lijiafu
 * @date 2020/3/30 23:14
 * @since 1.0
 */
@Slf4j
@Component
public class SmartCallJob {

    @Autowired
    private CallTaskService callTaskService;
    @Autowired
    private AliyunAcsClient aliyunAcsClient;
    @Autowired
    private KyRequest kyRequest;
    @Autowired
    private SmartCallService smartCallService;
    /**
     * 开元崔退任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    @Scheduled(cron = "0 0 12 * * ?")//中午12点执行
    //@Scheduled(cron = "0 12 21 * * ?")//中午12点执行
    public void backTask() {
        // 调用开元接口
        List<KyBackOrder> orderList = kyRequest.backOrder();
        log.info("定时获取开元崔退订单-------------start-----，数量 : {}",orderList.size());
        for(KyBackOrder kyBackOrder : orderList){
            CallTask callTask = callTaskService.getTaskByRequestId(kyBackOrder.getId() + "");
            if(null == callTask){
                //创建执行任务 TODO 商家id 和 外呼id
                CallTaskVo callTaskVo = new CallTaskVo();
                callTaskVo.setMerchantId(30);//商家id
                callTaskVo.setCallTypeId(1001l);//外呼实例id
                callTaskVo.setPhoneNum(Integer.parseInt(kyBackOrder.getRmno()));//被叫号码
                callTaskVo.setRequestId(kyBackOrder.getId()+"");//请求id
                callTaskVo.setRootNum(kyBackOrder.getRmno());//房间号
                Map<String,String> map  = new HashMap<>();
                map.put("memberType",KyRequest.vipNameMap.get(kyBackOrder.getMemberType()));
                map.put("dlayTime",KyRequest.vipTimeMap.get(kyBackOrder.getMemberType()));
                map.put("roomPhone",kyBackOrder.getRmno());
                callTaskVo.setExtParam(GsonUtil.getGsonInstance().toJson(map));
                try {
                    smartCallService.creatCallTask(callTaskVo);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("add task error ,requestId : {}",kyBackOrder.getId());
                }
            }
        }
        log.info("定时获取开元崔退订单-------------end-----,数量：{}",orderList.size());
    }

    /**
     * 开元叫早任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    //@Scheduled(cron = "0/20 * * * * ?")
    private void morningTask() {
        log.info("定时获取开元叫早订单-------------start-----");
        List<KyBackOrder> orderList = kyRequest.morningOrder();
        for(KyBackOrder kyBackOrder : orderList){
            CallTask callTask = callTaskService.getTaskByRequestId(kyBackOrder.getId() + "");
            if(null == callTask){
                //创建执行任务 TODO 商家id 和 外呼id
                CallTaskVo callTaskVo = new CallTaskVo();
                callTaskVo.setMerchantId(30);//商家id
                callTaskVo.setCallTypeId(1002l);//外呼实例id
                callTaskVo.setPhoneNum(Integer.parseInt(kyBackOrder.getRmno()));//被叫号码
                callTaskVo.setRequestId(kyBackOrder.getId()+"");//请求id
                callTaskVo.setExcuteTime(DateUtil.parseToDateTime(kyBackOrder.getMorningCallTime()));
                callTaskVo.setRootNum(kyBackOrder.getRmno());//房间号
                try {
                    smartCallService.creatCallTask(callTaskVo);
                } catch (BaseError baseError) {
                    baseError.printStackTrace();
                }
            }
        }
        log.info("定时获取开元叫早订单-------------end-----");
    }

    /**
     * 执行任务调用阿里创建任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    //@Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(fixedRate = 20000) //20秒执行一次
    private void excuteTask() {
        List<CallTask> excuteList = callTaskService.getExcuteList();
        if(CollectionUtils.isEmpty(excuteList)){
            return ;
        }
        //List 以instanceId分组 Map<String,List<CallTask>>
        Map<String, List<CallTask>> groupByTask = excuteList.stream().collect(Collectors.groupingBy(CallTask::getInstanceId));
        log.info("excute task start ..................task num : {}",groupByTask.size());
        //任务id集合
        Set<Integer> set = null;
        //遍历map集合
        for(Map.Entry<String, List<CallTask>> entry : groupByTask.entrySet()){
            //任务id集合
            set = new HashSet<>();
            //实例id
            String instanceId = entry.getKey();
            //任务列表
            List<CallTask> taskList = entry.getValue();
            //获取AssignJobsRequest
            AssignJobsRequest request =  setAssignJobsRequest(instanceId,taskList,set);
            AssignJobsResponse response = null;
            try {
                response  = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
                if(response.getHttpStatusCode().equals(200)){
                    //修改 callTask的状态为执行中
                    callTaskService.updateStatus(Byte.parseByte("1"),set);
                }

            } catch (ClientException e) {
                log.error("ErrCode: {},ErrMsg {},RequestId {}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            }

        }
        log.info("excute task end ，ids num : {}..................",set.size());
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
            //request.setRegionId("cn-shanghai");
            request.setInstanceId(task.getInstanceId());
            request.setJobGroupId(task.getGroupId());
            Long time = task.getExcuteTime().getTime();
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
                log.error("ErrCode: {},ErrMsg {},RequestId {}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            }
        }
        log.info("current excute task end ..................");
    }



    //拼装List<Extra>
    private List<Extra> setExtraList(CallTask task){
        List<Extra> extras = new ArrayList<>();
        Extra extra = new Extra();
        extra.setKey("taskId");
        extra.setValue(task.getId()+"");

        if(StringUtils.isNotBlank(task.getExtParam())){
            Map<String,String> map = GsonUtil.getGsonInstance().fromJson(task.getExtParam(),Map.class);
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            Extra temp = null;
            while (entries.hasNext()) {
                temp = new Extra();
                Map.Entry<String, String> entry = entries.next();
                temp.setKey(entry.getKey());
                temp.setValue(entry.getValue());
                extras.add(temp);
            }
        }
        extras.add(extra);
        return extras;
    }

    //拼装List<Contact>
    private List<Contact> setContactList(CallTask task){
        List<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact();
        contact.setPhonenumber(task.getPhoneNum()+"");
        contact.setReferenceId(task.getId()+"");
        contact.setName("n_"+task.getId());
        contact.setHonorific("n_"+task.getId());
        contacts.add(contact);
        return contacts;
    }

    //拼装请求阿里创建外呼任务的AssignJobsRequest
    private AssignJobsRequest setAssignJobsRequest(String instanceId,List<CallTask> taskList,Set<Integer> set){
        CallTask taskFirst  = taskList.get(0);
        AssignJobsRequest request = new AssignJobsRequest();
        request.setInstanceId(instanceId);
        request.setJobGroupId(taskFirst.getGroupId());
        List<String> callingNumberList = new ArrayList<String>();
        callingNumberList.add(taskFirst.getCallingNum()+"");
        request.setCallingNumbers(callingNumberList);
        List<String> jobsJsonList = new ArrayList<String>();
        String strategy ="";
        for(CallTask task : taskList){
            if(StringUtils.isNotBlank(task.getStrategy()) && StringUtils.isBlank(strategy)){
                strategy = task.getStrategy();
            }
            set.add(task.getId());
            JobsJson jobsJson = new JobsJson();
            List<Extra> extras = setExtraList(task);
            List<Contact> contacts = setContactList(task);
            jobsJson.setExtras(extras);
            jobsJson.setContacts(contacts);
            String json = GsonUtil.getGsonInstance().toJson(jobsJson);
            jobsJsonList.add(json);
        }
        request.setJobsJsons(jobsJsonList);
        request.setStrategyJson(strategy);

        return request;
    }

    public static void main(String[] args) {
        Date date = DateUtil.parseToDateTime(DateUtil.getDay(new Date()) + " 13:00:00");
        System.out.println(DateUtil.date2String(date));
        Long time = date.getTime();
        System.out.println(time);
        System.out.println(time + 5000l);
        //Map<String,String> map  = new HashMap<>();
        //map.put("memberType","我是中国人");
        //map.put("dlayTime","13：00");
        //String json = GsonUtil.getGsonInstance().toJson(map);
        //System.out.println(json);
        //Map<String,String> map1 = GsonUtil.getGsonInstance().fromJson(json,Map.class);
        //Iterator<Map.Entry<String, String>> entries = map1.entrySet().iterator();
        //while (entries.hasNext()) {
        //    Map.Entry<String, String> entry = entries.next();
        //    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        //}
    }
}
