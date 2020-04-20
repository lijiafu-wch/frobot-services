package f.s.fadminrobot.vo;

import java.util.List;

import lombok.Data;

/**
 *
 * @author lijiafu
 * @date 2020/3/15 16:46
 * @since 1.0
 */
@Data
public class SolutionVo {
    private Long solutionId;
    private String content;
    private String plainText;
    private List<String> perspectiveIds;
    private String action;
}
