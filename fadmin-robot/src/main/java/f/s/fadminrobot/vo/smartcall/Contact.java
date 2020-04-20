package f.s.fadminrobot.vo.smartcall;

import lombok.Data;

/**
 *  联系人
 *
 *  "phonenumber":"1005",
 *  "name":" 张三 ",
 *  "referenceId":"l09",
 *  "honorific":" 张先生 "
 * @author lijiafu
 * @date 2020/3/29 21:49
 * @since 1.0
 */
@Data
public class Contact {
    /**
     * 电话
     */
    private String phonenumber;
    /**
     * 姓名
     */
    private String name;
    /**
     * 尊称
     */
    private String honorific;
    /**
     * 业务id
     */
    private String referenceId;

}
