package f.s.frobot.vo;

import java.util.Date;

import lombok.Data;

/**
 * 机器人Vo
 * @author lijiafu
 * @date 2020/2/25 16:19
 * @since 1.0
 */
@Data
public class RobotInfoVo {

    private Long id;

    private Byte type;//机器人类型 1 在线文本  2 语音外呼  3 语音导航

    private String name;//机器人名称

    private String robotImg;//机器人图片

    private String aiImg;//ai图片-技能组图片

    private String welcome;//欢迎语

    private String h5Url;//H5链接

    private Byte status;//状态 0 未激活，1已激活 默认1

    private String instanceId;//阿里机器人id

    private String languageCode = "zh-cn";//机器人服务语言 zh-cn、en-us

    private String timeZone;//机器⼈的时区

    private String avatar;//机器⼈的头像

    private Date indate;//有效日期

    private Integer baseUserId;//商户id
}
