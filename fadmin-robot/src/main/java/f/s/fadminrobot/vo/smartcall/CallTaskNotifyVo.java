package f.s.fadminrobot.vo.smartcall;

import lombok.Data;

/**
 * 智能外呼通知结果vo
 * @author lijiafu
 * @date 2020/4/2 16:48
 * @since 1.0
 */
@Data
public class CallTaskNotifyVo {
    private Long merchantId;// 商户id
    private Integer taskId;// 任务id
    private String  result;//结果状态 1成功  2 失败  0 未知
    private boolean ifCall;//是否呼叫其他服务，true  false
    private Long callTypeId;//如果ifCall为true   智能外呼实例id，如： 崔退  叫早  前台  客房服务
    private String extParam;//如果ifCall为true 传递扩展参数，如客房服务，需要矿泉水，可输入矿泉水
}
