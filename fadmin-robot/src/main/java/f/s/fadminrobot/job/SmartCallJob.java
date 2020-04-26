package f.s.fadminrobot.job;

import f.s.fadminrobot.remote.KyRequest;
import f.s.fadminrobot.service.CallInstanceService;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.service.SmartCallService;
import f.s.fadminrobot.vo.ext.KyBackOrder;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.fadminrobot.vo.smartcall.Contact;
import f.s.fadminrobot.vo.smartcall.Extra;
import f.s.fadminrobot.vo.smartcall.JobsJson;
import f.s.frobot.model.CallInstance;
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
import com.aliyuncs.outboundbot.model.v20191226.CreateJobGroupRequest;
import com.aliyuncs.outboundbot.model.v20191226.CreateJobGroupResponse;
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
    private CallInstanceService callInstanceService;
    @Autowired
    private AliyunAcsClient aliyunAcsClient;


    /**
     * 执行任务调用阿里创建任务
     * @author lijiafu
     * @date 2020/3/30 23:18
     */
    //@Scheduled(cron = "0 */1 * * * ?")1分钟一次
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
                log.error("ExcuteTask ErrCode: {},ErrMsg {},RequestId {}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            }

        }
        log.info("excute task end ，ids num : {}..................",set.size());
    }

    /**
     * 每天生成一个作业组
     * @author lijiafu
     * @date 2020/4/26 21:57
     */
    @Scheduled(cron = "1 0 0 * * ?")//每天凌晨24:00:01点执行
    private void createJobGroup() {
        //获取所有有效的外呼实例
        List<CallInstance> callInstanceList = callInstanceService.findListByStatus();
        String currentDate = DateUtil.getDay();
        Date date = new Date();
        for (CallInstance callInstance : callInstanceList) {
            CreateJobGroupRequest request = new CreateJobGroupRequest();
            request.setInstanceId(callInstance.getInstanceId());
            request.setJobGroupName(currentDate);
            request.setJobGroupDescription(currentDate);
            request.setScriptId(callInstance.getScriptId());
            try {
                CreateJobGroupResponse response = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
                //作业组id
                String groupId = response.getJobGroup().getJobGroupId();
                CallInstance insterCallInstance = new CallInstance();
                insterCallInstance.setId(callInstance.getId());
                insterCallInstance.setGroupId(groupId);
                insterCallInstance.setGroupName(currentDate);
                insterCallInstance.setUtime(date);
                callInstanceService.update(insterCallInstance);
            } catch (ClientException e) {
                log.error("createJobGroup ErrCode: {},ErrMsg {},RequestId {}",e.getErrCode(),e.getErrMsg(),e.getRequestId());
            }
        }


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


    //拼装List<Contact>联系方式
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
}
