package f.s.fadminrobot.filter;

import f.s.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理和商户拦截器
 * @author lijiafu
 * @date 2020/3/2 10:53
 * @since 1.0
 */
@Component
public class BaseUserInterceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //将baseUserid放入当前线程的全局变量
        String userId = request.getHeader("userId");
        if(StringUtils.isNotBlank(userId)){
            CommonConfig.setBaseUserId(Integer.parseInt(userId));
        }
        return true;
    }
}
