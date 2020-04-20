package f.s.fadminrobot.vo;

import java.util.List;

import lombok.Data;

/**
 *
 * @author lijiafu
 * @date 2020/3/15 16:21
 * @since 1.0
 */
@Data
public class KonwledgeJsonVo {
    private Long knowledgeId;
    private Long categoryId;
    private String knowledgeTitle;
    private List<SolutionVo> solutions;
    private List<SimQuestionVo> simQuestions;
    private List<String> coreWords;

}
