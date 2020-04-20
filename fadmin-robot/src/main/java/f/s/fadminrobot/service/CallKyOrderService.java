package f.s.fadminrobot.service;

import f.s.frobot.model.CallKyOrder;
import java.util.List;

/**
 * 开元订单服务
 * @author Administrator
 * @date 2020/4/2 21:46
 * @since 1.0.0
 */
public interface CallKyOrderService {

    /**
     * 批量添加订单
     * @author lijiafu
     * @date 2020/4/2 21:47
     */
    void batchAdd(List<CallKyOrder> orderList);

    /**
     * 根据订单id获取开元订单
     * @author lijiafu
     * @date 2020/4/2 22:09
     */
    CallKyOrder findByOrderId(Integer orderId);
}
