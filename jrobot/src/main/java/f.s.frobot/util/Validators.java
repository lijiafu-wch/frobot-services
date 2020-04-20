package f.s.frobot.util;

import java.util.Collection;
import java.util.Map;

public class Validators {
    /**
     * 校验对象是否为空
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof String) {
            return ((String) o).length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection) o).size() == 0;
        } else if (o instanceof Map) {
            return ((Map) o).size() == 0;
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        } else {
            return false;
        }
    }
}
