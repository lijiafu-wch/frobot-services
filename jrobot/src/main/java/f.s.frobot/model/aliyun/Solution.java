package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 * 文本型答案
 * @author lijiafu
 * @date 2020/2/28 12:20
 * @since 1.0
 */
@Data
public class Solution {

    private Long SolutionId;// 答案id
    private String ModifyTime;//  修改时间(UTC 时间)
    private String  CreateTime;// 创建时间(UTC 时间)
    private String Content;//富⽂本内容
    private String PlainText;//纯⽂本内容
    private String Summary;//摘要
    private List<String> PerspectiveIds;//视⻆id列表
    private String Action;//操作类型：追加： ADD，修改：UPD，移除：DEL
}
