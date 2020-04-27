package f.s.fadminrobot;

import f.s.fadminrobot.job.KyCallJob;
import f.s.fadminrobot.job.SmartCallJob;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 开元job测试
 * @author lijiafu
 * @date 2020/4/27 21:52
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KyJobTest {

    @Autowired
    private SmartCallJob smatCallJob;
    @Autowired
    private KyCallJob kyCallJob;

    @Test
    public void kyOrder(){
        kyCallJob.backTask();
    }


    @Test
    public void createJobGroup(){
        smatCallJob.createJobGroup();
    }
}
