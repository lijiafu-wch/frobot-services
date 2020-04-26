package f.s.fadminrobot.aop;

import f.s.utils.StringUtils;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *  拦截RequestDecode注解，进行参数解密，并重新赋值
 * @author lijiafu
 * @date 2020/4/23 17:06
 * @since 1.0
 */
//@Aspect
//@Component
public class CallApiDecodeAop {

    @Pointcut("@annotation(f.s.fadminrobot.aop.RequestDecode)")
    public void requestDecode() { };

    @Around("requestDecode()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if(null != args && args.length==1){
            //解密，并重新赋值参数
            args[0]=getDecodeData();;
        }
        return point.proceed(args);
    }

    //根据请求头中商户编号不同，获取密钥进行解密
    private String getDecodeData(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String idNum = request.getHeader("idNum");
        //TODO 根据idNum找到对应的商户的密钥，对encryptData进行解密

        //对参数进行解密，重新赋值
        String decodeStr = "";
        return decodeStr;
    }
}
