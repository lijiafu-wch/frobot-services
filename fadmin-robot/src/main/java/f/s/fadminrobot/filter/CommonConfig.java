package f.s.fadminrobot.filter;

/**
 * 公共的ThreadLocal配置
 * @author lijiafu
 * @date 2020/3/2 10:45
 */
public abstract class CommonConfig {
    /**
     * 用于web请求获取baseUser
     */
    private static final ThreadLocal<Integer> baseUserId = new ThreadLocal<>();

    public static Integer getBaseUserId() {
        return  baseUserId.get();
    }
    public static void setBaseUserId(Integer userId) {
        baseUserId.set(userId);
    }

    public static void removeBaseUserId() {
        baseUserId.remove();
    }
}
