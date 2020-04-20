package f.s.fadminrobot;

import f.s.frobot.util.AliyunAcsClient;
import f.s.frobot.util.AliyunUtil;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.outboundbot.model.v20191226.AssignJobsRequest;
import com.aliyuncs.outboundbot.model.v20191226.AssignJobsResponse;
import com.aliyuncs.outboundbot.model.v20191226.CreateJobGroupRequest;
import com.aliyuncs.outboundbot.model.v20191226.CreateJobGroupResponse;
import com.aliyuncs.outboundbot.model.v20191226.ListInstancesRequest;
import com.aliyuncs.outboundbot.model.v20191226.ListInstancesResponse;
import com.aliyuncs.outboundbot.model.v20191226.ListJobGroupsRequest;
import com.aliyuncs.outboundbot.model.v20191226.ListJobGroupsResponse;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsRequest;
import com.aliyuncs.outboundbot.model.v20191226.QueryJobsResponse;
import com.aliyuncs.outboundbot.model.v20191226.StartJobRequest;
import com.aliyuncs.outboundbot.model.v20191226.StartJobResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

/**
 *
 * @author lijiafu
 * @date 2020/3/26 22:59
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallTest {

    @Autowired
    private AliyunAcsClient aliyunAcsClient;

    /**
     * 实例列表
     * @author lijiafu
     * @date 2020/3/27 22:36
     */
    @Test
    public void  listInstances() throws Exception {
        ListInstancesRequest request = new ListInstancesRequest();
        try {
            ListInstancesResponse response = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 外呼作业组列表
     * @author lijiafu
     * @date 2020/3/27 22:36
     */
    @Test
    public void  listJobGroups() throws Exception {
        ListJobGroupsRequest request = new ListJobGroupsRequest();
        request.setRegionId("cn-shanghai");
        request.setInstanceId("216f3937-b928-47c5-9dac-6d5e74f8fbbc");
        request.setPageNumber(1);
        request.setPageSize(10);

        try {
            ListJobGroupsResponse response = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 创建作业组
     * @author lijiafu
     * @date 2020/3/27 22:36
     */
    @Test
    public void  createJobGroup() throws Exception {
        CreateJobGroupRequest request = new CreateJobGroupRequest();
        request.setRegionId("cn-shanghai");
        request.setInstanceId("216f3937-b928-47c5-9dac-6d5e74f8fbbc");
        request.setJobGroupName("开元崔退测试2");
        request.setJobGroupDescription("开元崔退测试2");
        request.setScriptId("d1db100a-abfb-41fb-b60d-f06578f04117");
        //request.setStrategyJson("{\"maxAttemptsPerDay\":2,\"name\":\" 策略名字 \",\"workingTime\":[\"beginTime\":\"23:00:00\",\"endTime\":\"23:59:00\"}],\"minAttemptInterval\":60}");
        try {
            CreateJobGroupResponse response = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 批量创建外呼任务
     * @author lijiafu
     * @date 2020/3/27 22:36
     */
    @Test
    public void  assignJobs() throws Exception {
        AssignJobsRequest request = new AssignJobsRequest();
        request.setRegionId("cn-shanghai");
        request.setInstanceId("216f3937-b928-47c5-9dac-6d5e74f8fbbc");
        request.setJobGroupId("f8a3cf6f-5c54-45ae-b355-ca7ff4d1e3ba");

        List<String> jobsJsonList = new ArrayList<String>();
        jobsJsonList.add("{\"extras\":[{\"value\":\"ljf\",\"key\":\"001\"}],\"contacts\":[{\"phonenumber\":\"1005\",\"name\":\" 张三 \"," +
                "\"referenceId\":\"l01\",\"honorific\":\" 张先生 \"}]}");
        request.setJobsJsons(jobsJsonList);

        List<String> callingNumberList = new ArrayList<String>();
        callingNumberList.add("85066666");
        request.setCallingNumbers(callingNumberList);

        try {
            AssignJobsResponse response  = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

    /**
     * 查询外呼任务状态
     * @author lijiafu
     * @date 2020/3/27 22:36
     */
    @Test
    public void  queryJobs() throws Exception {
        QueryJobsRequest request = new QueryJobsRequest();
        request.setRegionId("cn-shanghai");
        request.setInstanceId("216f3937-b928-47c5-9dac-6d5e74f8fbbc");
        request.setJobGroupId("58075518-6420-437f-89fa-a2cc5f0c7f66");
        request.setStartTime(1586526601000L);
        request.setEndTime(1586526841000L);
        request.setTimeAlignment("start");
        request.setPhoneNumber("1005");
        request.setPageNumber(1);
        request.setPageSize(1);
        try {
            QueryJobsResponse response  = aliyunAcsClient.getIAcsClient().getAcsResponse(request);
            List list = response.getJobs().getList();
            if(!CollectionUtils.isEmpty(list)){
                QueryJobsResponse.Jobs.Job job = (QueryJobsResponse.Jobs.Job) list.get(0);
                System.out.println("返回状态："+job.getStatus());
            }
            System.out.println(new Gson().toJson(response));
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }

}
