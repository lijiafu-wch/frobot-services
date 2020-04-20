package f.s.frobot.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 属性传递工具类
 * @author lijiafu
 * @date 2019/12/31 11:51
 * @since 1.0
 */
public class PropertyTransmitUtil {

    /**
     * 父类属性，同步给子类
     * @author lijiafu
     * @date 2019/12/31 11:49
     */
    public static <T>void fatherToChild(T father,T child) throws Exception {
        if (child.getClass().getSuperclass() != father.getClass()) {
            throw new Exception("child 不是 father 的子类");
        }
        Class<?> fatherClass = father.getClass();
        Field[] declaredFields = fatherClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Method method = fatherClass.getDeclaredMethod("get" + upperHeadChar(field.getName()));
            Object obj = method.invoke(father);
            field.setAccessible(true);
            field.set(child, obj);
        }
    }
    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in) {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }
}
