package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.CallApiService;
import f.s.fadminrobot.service.CallTaskService;
import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.frobot.model.CallTask;
import f.s.jerror.BaseError;
import f.s.utils.StringUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * 智能外呼
 * @author lijiafu
 * @date 2020/3/29 21:25
 * @since 1.0
 */
@Slf4j
@Service
public class CallApiServiceImpl implements CallApiService {
    @Autowired
    private CallTaskService callTaskService;
    @Autowired
    private RestTemplate restTemplate;


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
        //TODO 找到对应的回调url
        String url ="";
        Map<String,String> map = new HashMap<>();
        map.put("result","result");
        ResponseEntity<String> entity = restTemplate.postForEntity(url, map, String.class);
        String  returnResult  =entity.getBody();
        //成功返回
        if(StringUtils.isNotBlank(returnResult) || returnResult.equals("ok")){
            //更新任务状态
            CallTask tempTask = new CallTask();
            tempTask.setId(callTaskNotifyVo.getTaskId());
            tempTask.setStatus(Byte.parseByte("2"));//已执行
            tempTask.setResult(callTaskNotifyVo.getResult());
            tempTask.setNotifyResult(returnResult);
            tempTask.setUtime(new Date());
            callTaskService.updateCallTask(tempTask);
        }
        log.info("订单号：{},开元通知结果 {}",callTaskNotifyVo.getTaskId(),returnResult);

    }
}
