package f.s.fadminrobot.aop;/**
 * @author Administrator
 * @date 2020/4/23 17:50
 * @since 1.0.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author lijiafu
 * @date 2020/4/23 17:50
 * @since 1.0
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestDecode {

    boolean value() default true;
}
