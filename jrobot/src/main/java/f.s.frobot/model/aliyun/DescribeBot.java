package f.s.frobot.model.aliyun;

import java.util.List;
import lombok.Data;

/**
 * 机器人详情
 * @author lijiafu
 * @date 2020/2/18 21:38
 * @since 1.0
 */
@Data
public class DescribeBot {
    private String InstanceId; //公有云问答API，机器⼈唯⼀标识
    private String Name;//机器⼈名称
    private String Introduction;//机器⼈备注
    private String Timezone;//机器⼈的时区，参考《公共-时区码》
    private String LanguageCode;//机器⼈服务的语⾔，如zh-cn、en-us
    private String Avatar;//机器⼈头像的URL
    private String CreateTime;//机器⼈创建的 UTC 时间
    private List<Category> Categories;//类⽬列表
}
