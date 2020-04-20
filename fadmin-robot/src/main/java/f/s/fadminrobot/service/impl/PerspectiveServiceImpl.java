package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.PerspectiveService;
import f.s.frobot.model.aliyun.Perspectives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lijiafu
 * @date 2020/2/26 15:50
 * @since 1.0
 */
@Service
public class PerspectiveServiceImpl implements PerspectiveService {

    @Autowired
    private BotService botService;


    /**
     * 视角列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Perspectives List() throws Exception {
        Perspectives perspectives = botService.queryPerspectives();
        return perspectives;
    }
}
