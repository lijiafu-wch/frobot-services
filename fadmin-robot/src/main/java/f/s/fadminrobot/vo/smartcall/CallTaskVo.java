package f.s.fadminrobot.vo.smartcall;

import java.util.Date;

import lombok.Data;

/**
 * 呼叫任务Vo
 * @author lijiafu
 * @date 2020/4/2 16:48
 * @since 1.0
 */
@Data
public class CallTaskVo {

    private Integer merchantId;// 商户id
    private String instanceId;//实例id 不需要传
    private String groupId;//组id 不需要传
    private Integer callingNum;//主叫号码 不需要传
    private Integer phoneNum;//被叫号码
    private String rootNum;//房间号
    private String requestId;//第三方id
    private Date excuteTime;//执行时间 立即执行传空
    private Long callTypeId;//根据商户id匹配呼叫类型id，如： 崔退  叫早  前台  客房服务
    private String extParam;//扩展参数，如客房服务，需要矿泉水，可输入矿泉水
}
