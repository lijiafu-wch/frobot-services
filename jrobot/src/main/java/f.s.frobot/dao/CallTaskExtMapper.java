package f.s.frobot.dao;

import f.s.frobot.model.CallTask;
import f.s.frobot.model.CallTaskExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface CallTaskExtMapper extends CallTaskMapper{

    /**
     * 获取任务执行列表
     * @author lijiafu
     * @date 2020/4/1 20:49
     */
    List<CallTask> getExcuteList();


    /**
     * 获取没有小蜜返回结果的任务列表
     * @author lijiafu
     * @date 2020/4/11 21:45
     */
    List<CallTask> getCurrentExcuteList(@Param("callInstanceId") Integer callInstanceId);
    /**
     * 修改任务状态
     * @author lijiafu
     * @date 2020/4/1 22:12
     */
    int updateStatus(@Param("status") Byte status,@Param("ids") Set<Integer> ids);

    }