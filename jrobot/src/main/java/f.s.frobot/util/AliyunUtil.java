package f.s.frobot.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 *
 * @author lijiafu
 * @date 2020/2/18 15:25
 * @since 1.0
 */
public class AliyunUtil {

    /**
     * 获取时间戳
     * @author lijiafu
     * @date 2020/2/18 15:27
     */
    public static String getTimestamp(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        return simpleDateFormat.format(date);
    }


}
