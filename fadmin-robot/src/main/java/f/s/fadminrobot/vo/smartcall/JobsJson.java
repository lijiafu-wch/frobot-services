package f.s.fadminrobot.vo.smartcall;

import java.util.List;

import lombok.Data;

/**
 * 作业数据
 * @author lijiafu
 * @date 2020/3/29 21:40
 * @since 1.0
 */
@Data
public class JobsJson {
    /**
     * 附加参数
     */
    private List<Extra> extras;
    /**
     * 联系人集合
     */
    private List<Contact> contacts;
}
