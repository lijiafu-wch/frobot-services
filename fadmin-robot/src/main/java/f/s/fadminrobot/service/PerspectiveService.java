package f.s.fadminrobot.service;

import f.s.frobot.model.aliyun.Perspectives;

/**
 *  视角接口
 * @author lijiafu
 * @date 2020/2/18 16:27
 * @since 1.0
 */
public interface PerspectiveService {

    /**
     * 视角列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Perspectives List() throws Exception;
}
