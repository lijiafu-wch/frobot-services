package f.s.fadminrobot.service;

import f.s.frobot.model.CallTask;
import f.s.jerror.BaseError;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @date 2020/4/1 19:50
 * @since 1.0.0
 */
public interface CallTaskService {

    /**
     * 获取任务，根据id
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    CallTask findById(Integer id);

    /**
     * 添加任务
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
     void add(CallTask callTask) throws BaseError;

    /**
     * 获取任务执行列表
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
     List<CallTask> getExcuteList();

    /**
     * 获取没有小蜜返回结果的任务列表
     * @author lijiafu
     * @date 2020/4/11 21:45
     */
    List<CallTask> getCurrentExcuteList(Integer callInstanceId);

    /**
     * 根据第三方id获取任务
     * @author lijiafu
     * @date 2020/3/31 0:05
     */
    CallTask  getTaskByRequestId(String requestId);


    /**
     * 修改任务执行状态
     * @param status 执行状态 0-未执行,1-执行中,2-已执行
     * @author lijiafu
     * @date 2020/4/1 22:12
     */
    int updateStatus(Byte status, Set<Integer> ids);

    /**
     * 修改任务
     * @author lijiafu
     * @date 2020/4/1 22:12
     */
    int updateCallTask(CallTask callTask);
}
