package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.CallInstanceService;
import f.s.frobot.dao.CallInstanceMapper;
import f.s.frobot.model.CallInstance;
import f.s.frobot.model.CallInstanceExample;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 外呼实例实现类
 * @author lijiafu
 * @date 2020/4/2 17:39
 * @since 1.0
 */
@Service
public class CallInstanceServiceImpl implements CallInstanceService {

    @Autowired
    private CallInstanceMapper callInstanceMapper;
    /**
     * 根据id，查询外呼实例
     * @author lijiafu
     * @date 2020/4/2 17:40
     */
    @Override
    public CallInstance findById(Long id) {
        return callInstanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改实例
     * @author lijiafu
     * @date 2020/4/2 17:40
     */
    @Override
    public int update(CallInstance callInstance) {
        return callInstanceMapper.updateByPrimaryKeySelective(callInstance);
    }

    /**
     * 查询所有有效的外呼实例
     * @author lijiafu
     * @date 2020/4/26 22:05
     */
    @Override
    public List<CallInstance> findListByStatus(){
        CallInstanceExample example = new CallInstanceExample();
        //TODO 数字需要配置，查询有效的外呼实例
        example.createCriteria().andStatusEqualTo(Byte.parseByte("1"));
        List<CallInstance> list = callInstanceMapper.selectByExample(example);
        return list;
    }
}
