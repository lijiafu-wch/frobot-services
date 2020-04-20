package f.s.fadminrobot.util;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;

/**
 * @author  HSL
 * 切面获取pageIndex,pageSize赋值给PageHelper
 */
@Aspect
@Component
public class PageAspect {

    @Pointcut("execution(* f.s.fadminrobot.service.impl..*.*(..)) && @annotation(autoPage)")
    public void business(AutoPage autoPage) {
    }
    @Around("business(autoPage)")
    public Object around(ProceedingJoinPoint pjp, AutoPage autoPage) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String pageNum = request.getParameter(autoPage.pageIndex());
        String pageSize = request.getParameter(autoPage.pageSize());
        PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        return pjp.proceed(pjp.getArgs());
    }
}
