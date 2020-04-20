package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.CallKyOrderService;
import f.s.frobot.dao.CallKyOrderMapper;
import f.s.frobot.model.CallKyOrder;
import f.s.frobot.model.CallKyOrderExample;
import f.s.frobot.model.CallTask;
import f.s.frobot.model.CallTaskExample;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 开元订单服务实现类
 * @author lijiafu
 * @date 2020/4/2 21:43
 * @since 1.0
 */
@Service
public class CallKyOrderServiceImpl implements CallKyOrderService {

    @Autowired
    private CallKyOrderMapper callKyOrderMapper;

    /**
     * 批量添加订单
     * @author lijiafu
     * @date 2020/4/2 21:47
     */
    @Override
    public void batchAdd(List<CallKyOrder> orderList){
        callKyOrderMapper.batchInsert(orderList);
    }

    /**
     * 根据订单id获取开元订单
     * @author lijiafu
     * @date 2020/4/2 22:09
     */
    @Override
    public CallKyOrder findByOrderId(Integer orderId){
        CallKyOrderExample example = new CallKyOrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        CallKyOrder callKyOrder = callKyOrderMapper.selectOneByExample(example);
        return callKyOrder;
    }
}
