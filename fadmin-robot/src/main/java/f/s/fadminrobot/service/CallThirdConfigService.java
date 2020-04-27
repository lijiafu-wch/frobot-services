package f.s.fadminrobot.service;

import f.s.frobot.model.CallThirdConfig;
import java.util.List;

/**
 * 第三方接口配置
 * @author lijiafu
 * @date 2020/4/27 17:58
 */
public interface CallThirdConfigService {

    /**
     * 获取属性值，根据商户id、参数名称、属性key
     * @author lijiafu
     * @date 2020/4/27 17:55
     */
    String getValue(Integer baseUserId,String name,String key);

    /**
     * 获取属性列表，根据商户id、参数名称
     * @author lijiafu
     * @date 2020/4/27 17:55
     */
    List<CallThirdConfig> getValueList(Integer baseUserId, String name);
}
