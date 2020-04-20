package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 * 机器人列表
 * @author lijiafu
 * @date 2020/2/18 15:59
 * @since 1.0
 */
@Data
public class QueryBots {
    private Integer PageNumber; //分⻚-第⼏⻚
    private Integer PageSize; //分⻚-⻚⾯⼤⼩
    private Integer TotalCount; //总条数
    private List<Bot> Bots;//机器⼈列表信息
}
