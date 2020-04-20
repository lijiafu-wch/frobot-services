package f.s.frobot.util;

import com.google.gson.Gson;

/**
 *  Gson工具类
 * @author lijiafu
 * @date 2020/2/18 16:39
 * @since 1.0
 */
public class GsonUtil {

    private static class GsonHolder{
        private static final Gson INSTANCE = new Gson();
    }

    /**
     * 获取Gson实例，由于Gson是线程安全的，这里共同使用同一个Gson实例
     */
    public static Gson getGsonInstance(){
        return GsonHolder.INSTANCE;
    }
}
