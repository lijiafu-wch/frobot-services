package f.s.frobot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务监控检查
 * @author lijiafu
 * @date 2019/12/31 11:06
 */
@RestController
@RequestMapping("/svc")
public class SVCController extends BaseController {

    @RequestMapping("/status")
    public String status(){
        return "OK";
    }
}
