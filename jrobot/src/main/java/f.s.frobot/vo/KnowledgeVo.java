package f.s.frobot.vo;

import lombok.Data;

/**
 * 知识vo
 * @author lijiafu
 * @date 2020/2/26 16:26
 * @since 1.0
 */
@Data
public class KnowledgeVo {

    private Long categoryId;//类⽬ID
    private Long knowledgeId;//知识id
    private String knowledgeTitle;//知识标题
    private String coreWordName;//核⼼词

}
