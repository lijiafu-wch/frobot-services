package f.s.fadminrobot.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自动分页
 * @author HSL
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoPage {

    String pageIndex() default "pageIndex";
    String pageSize() default "pageSize";

}
