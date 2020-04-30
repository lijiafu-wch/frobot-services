package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.CallThirdConfigService;
import f.s.frobot.dao.CallThirdConfigMapper;
import f.s.frobot.model.CallThirdConfig;
import f.s.frobot.model.CallThirdConfigExample;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 第三方接口配置
 * @author lijiafu
 * @date 2020/4/27 17:49
 * @since 1.0
 */
@Slf4j
@Service
public class CallThirdConfigServiceImpl implements CallThirdConfigService {
    @Autowired
    private CallThirdConfigMapper callThirdConfigMapper;

    /**
     * 获取属性值，根据商户id、参数名称、属性key
     * @author lijiafu
     * @date 2020/4/27 17:55
     */
    @Override
    //@Cacheable(key="RedisConstant.THIRD_KY_HOTLE+#baseUserId+':'+#name+':'+#key")
    public String getValue(Integer baseUserId,String name,String key){
        CallThirdConfigExample example = new CallThirdConfigExample();
        example.createCriteria().andBaseUserIdEqualTo(baseUserId).andNameEqualTo(name).andKEqualTo(key);
        CallThirdConfig callThirdConfig = callThirdConfigMapper.selectOneByExample(example);
        return callThirdConfig.getV();
    }

    /**
     * 获取属性列表，根据商户id、参数名称
     * @author lijiafu
     * @date 2020/4/27 17:55
     */
    //@Cacheable(key="RedisConstant.THIRD_KY_HOTLE+#baseUserId+':'+#name")
    @Override
    public List<CallThirdConfig> getValueList(Integer baseUserId,String name){
        CallThirdConfigExample example = new CallThirdConfigExample();
        example.createCriteria().andBaseUserIdEqualTo(baseUserId).andNameEqualTo(name);
        List<CallThirdConfig> list = callThirdConfigMapper.selectByExample(example);
        return list;
    }

}
