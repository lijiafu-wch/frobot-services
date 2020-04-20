package f.s.frobot.model.aliyun;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 机器人信息
 * @author lijiafu
 * @date 2020/2/18 16:02
 * @since 1.0
 */
@Data
public class Bot {
    private String InstanceId;//公有云问答API，机器⼈唯⼀标识
    private String Name;//机器⼈名称
    private String Introduction; //机器⼈备注
    private String Timezone; //机器⼈的时区，参考《公共-时区码》
    private String LanguageCode;//机器⼈服务的语⾔，如zh-cn、en-us
    private String Avatar;//机器⼈头像的URL
    private String CreateTime;//机器⼈创建的 UTC 时间
    private String robotType;//机器人类型 1 在线文本  2 语音外呼  3 语音导航
    private String robotImg;//机器人图片
    private String aiImg;//技能组图片
    private String welcome;//欢迎语
}
