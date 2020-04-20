package f.s.frobot.model.aliyun;

import lombok.Data;

/**
 * 响应结果
 * @author lijiafu
 * @date 2020/2/28 22:02
 * @since 1.0
 */
@Data
public class ResponseResult {

    private String RequestId;//请求id
    private Long KnowledgeId;//知识id
    private Long CategoryId;//类目id
    private Boolean Success;//是否操作成功
    private String InstanceId;//机器人id
}
