package f.s.fadminrobot.service;

import f.s.frobot.model.CallInstance;

/**
 * 外呼实例service
 * @author lijiafu
 * @date 2020/4/2 17:38
 * @since 1.0
 */
public interface CallInstanceService {

    /**
     * 根据id，查询外呼实例
     * @author lijiafu
     * @date 2020/4/2 17:40
     */
    CallInstance findById(Long id);
}
