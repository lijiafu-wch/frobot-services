package f.s.fadminrobot;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;

/**
 *
 * @author lijiafu
 * @date 2020/4/1 16:45
 * @since 1.0
 */
@Data
public class KyBackOrder {
    private String arr; //入住时间
    private String memberNo;//会员卡号
    private String mobile;//客户手机号
    private String name;//客户姓名
    private String rmno;//房间号
    private Integer id;//订单id
    private String memberType;//会员级别
    private String morningCallTime;//叫醒时间
    private String dep;//离店时间

}
