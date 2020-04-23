package f.s.fadminrobot.service;

import f.s.fadminrobot.vo.smartcall.CallTaskNotifyVo;
import f.s.jerror.BaseError;

/**
 * @author Administrator
 * @date 2020/4/23 15:01
 * @since 1.0.0
 */
public interface CallApiService {

    /**
     * 结果通知
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void notify(CallTaskNotifyVo callTaskNotifyVo) throws BaseError;
}
