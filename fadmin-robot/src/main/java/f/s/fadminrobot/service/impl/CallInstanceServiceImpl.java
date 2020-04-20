package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.CallInstanceService;
import f.s.frobot.dao.CallInstanceMapper;
import f.s.frobot.model.CallInstance;

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
}
