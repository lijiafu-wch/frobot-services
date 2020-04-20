package f.s.fadminrobot.service;

import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.fadminrobot.vo.smartcall.CallTaskVo;
import f.s.frobot.model.CallTask;
import f.s.jerror.BaseError;

/**
 * 智能外呼接口
 * @author ljf
 * @date 2020/3/29 21:02
 * @since 1.0.0
 */
public interface SmartCallService {

    /**
     * 创建服务
     * @author lijiafu
     * @date 2020/4/2 0:22
     */
    void creatCallTask(CallTaskVo callTaskVo) throws BaseError;

    /**
     * 复制一个任务
     * @author lijiafu
     * @date 2020/4/2 0:22
     */
    void copyCallTask(CallTask callTask) throws BaseError;


    /**
     * 结果通知
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void notify(CallTaskNotifyVo callTaskNotifyVo) throws BaseError;
}
