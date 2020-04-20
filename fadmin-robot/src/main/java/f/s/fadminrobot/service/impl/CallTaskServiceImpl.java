package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.fadminrobot.filter.CommonConfig;
import f.s.fadminrobot.service.CallTaskService;
import f.s.frobot.dao.CallTaskExtMapper;
import f.s.frobot.model.CallTask;
import f.s.frobot.model.CallTaskExample;
import f.s.frobot.model.RobotCategoryRelation;
import f.s.frobot.model.RobotCategoryRelationExample;
import f.s.frobot.model.RobotInfo;
import f.s.frobot.model.RobotInfoExample;
import f.s.frobot.util.GsonUtil;
import f.s.frobot.util.Validators;
import f.s.jerror.BaseError;
import f.s.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

/**
 *
 * @author lijiafu
 * @date 2020/3/31 0:02
 * @since 1.0
 */
@Service
public class CallTaskServiceImpl implements CallTaskService {

    @Autowired
    private CallTaskExtMapper callTaskMapper;
    @Autowired
    private FrobotErrors frobotErrors;

    /**
     * 获取任务，根据id
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    @Override
    public CallTask findById(Integer id){
        return callTaskMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加任务
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    public void add(CallTask callTask) throws BaseError {
        //限制附加参数最多20个
        if(StringUtils.isNotBlank(callTask.getExtParam())){
            Map<String,String> map = GsonUtil.getGsonInstance().fromJson(callTask.getExtParam(), Map.class);
            if(map.size()>20){
                throw frobotErrors.ExtParamOutOfRange();
            }
        }
        callTaskMapper.insertSelective(callTask);

    }


    /**
     * 获取任务执行列表
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    @Override
    public List<CallTask> getExcuteList(){
        return callTaskMapper.getExcuteList();
    }

    /**
     * 获取没有小蜜返回结果的任务列表
     * @author lijiafu
     * @date 2020/4/11 21:45
     */
    @Override
    public List<CallTask> getCurrentExcuteList(Integer callInstanceId){
        return callTaskMapper.getCurrentExcuteList(callInstanceId);
    }
    /**
     * 根据第三方id获取任务
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    @Override
    public  CallTask  getTaskByRequestId(String requestId){
        CallTaskExample example = new CallTaskExample();
        example.createCriteria().andRequestidEqualTo(requestId);
        CallTask callTask = callTaskMapper.selectOneByExampleSelective(example,
                CallTask.Column.requestid);
        return callTask;
    }

    /**
     * 修改任务执行状态
     * @param status 执行状态 0-未执行,1-执行中,2-已执行
     * @author lijiafu
     * @date 2020/4/1 22:12
     */
    @Override
    public int updateStatus(Byte status,Set<Integer> ids){
        return callTaskMapper.updateStatus(status,ids);
    }

    /**
     * 修改任务
     * @author lijiafu
     * @date 2020/4/1 22:12
     */
    @Override
    public int updateCallTask(CallTask callTask){
        return callTaskMapper.updateByPrimaryKeySelective(callTask);
    }

}
