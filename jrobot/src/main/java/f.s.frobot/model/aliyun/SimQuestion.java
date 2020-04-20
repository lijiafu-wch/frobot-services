package f.s.frobot.model.aliyun;

import lombok.Data;

/**
 * 相似问题
 * @author lijiafu
 * @date 2020/2/28 12:18
 * @since 1.0
 */
@Data
public class SimQuestion {

    private Long SimQuestionId;//相似问法id
    private String ModifyTime;// 修改时间(UTC 时间)
    private String CreateTime;// 创建时间(UTC 时间)
    private String Title;//相似问法标题
}
