package f.s.frobot.fenum;

/**
 * 机器人类型
 * @author lijiafu
 * @date 2019/12/31 16:28
 */
public enum RobotTypeEnum {

    MOBILE(1,"scenario_im"),//在线文本
    HOTEL(2,"scenario_callout"),//语音外呼
    ILLNESS(3,"scenario_ivr");//语音导航

    private final Integer code;

    private final String value;

    private RobotTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * 根据code获取值
     * @author lijiafu
     * @date 2020/2/25 17:43
     */
    public static String getValue(Integer code) {
        RobotTypeEnum[] robotTypeEnums = values();
        for (RobotTypeEnum robotTypeEnum : robotTypeEnums) {
            if (robotTypeEnum.getCode().equals(code)) {
                return robotTypeEnum.getValue();
            }
        }
        return null;
    }
}
