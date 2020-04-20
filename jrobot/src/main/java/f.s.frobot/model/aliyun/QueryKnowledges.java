package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 * 获取知识列表
 * @author lijiafu
 * @date 2020/2/26 16:17
 * @since 1.0
 */
@Data
public class QueryKnowledges {

    private List<Knowledge> Knowledges;//知识
    private Integer PageNumber;//分⻚-第⼏⻚
    private Integer PageSize;//分⻚-⻚⾯⼤⼩
    private Integer TotalCount;//分⻚-总条数

}
