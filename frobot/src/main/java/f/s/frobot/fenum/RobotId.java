package f.s.frobot.fenum;

/**
 * 机器人类型
 * @author lijiafu
 * @date 2019/12/31 16:28
 */
public enum RobotId {

    MOBILE("HZ-MOBILE","chatbot-cn-JBuaaiN2zb"),//移动云机器人
    HOTEL("KY-HOTEL","chatbot-cn-MeEReayVJf"),//开元酒店机器人
    ILLNESS("ILLNESS-INFORMATION","chatbot-cn-MQQmF37eib");//疾控百问机器人

    private final String code;

    private final String value;

    private RobotId(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
