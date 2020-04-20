package f.s.frobot.util;

import java.util.HashMap;

/**
 * 机器人工具类
 * @author lijiafu
 * @date 2020/1/30 16:53
 * @since 1.0
 */
public class RobotUtil {

    private  volatile static HashMap<String,String> robotMap;

    public static HashMap<String,String> getRobotMap(){
        if (robotMap == null) {
            synchronized (RobotUtil.class) {
                if (robotMap == null) {
                    robotMap = new HashMap<String,String>();
                    robotMap.put("HZ-MOBILE","chatbot-cn-JBuaaiN2zb");
                    robotMap.put("KY-HOTEL","chatbot-cn-MeEReayVJf");
                    robotMap.put("ILLNESS-INFORMATION","chatbot-cn-MQQmF37eib");
                }
            }
        }
        return robotMap;
    }
}
