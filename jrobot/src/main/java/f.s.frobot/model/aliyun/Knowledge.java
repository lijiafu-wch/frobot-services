package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 * 知识
 * @author lijiafu
 * @date 2020/2/26 16:18
 * @since 1.0
 */
@Data
public class Knowledge {

    private Long KnowledgeId;//知识ID
    private String ModifyTime;//修改时间（UTC时间）
    private String ModifyUserName;//修改⼈
    private String CreateTime;//创建时间（UTC时间）
    private String CreateUserName;//创建⼈
    private Long CategoryId;//类⽬ID
    private Integer KnowledgeStatus;//知识状态：3：已发布，-1：全部，14：草稿，17：已失效，15：待发布(发布中)
    private String KnowledgeTitle;//标题
    private List<String> CoreWords;//核⼼词
    private Integer KnowledgeType;//知识类型:1：普通知识 5：⽂档知识（私有云）
    private String StartDate;//⽣效时间（UTC时间）
    private String EndDate;//失效时间（UTC时间）
    private Integer Version;//版本号（最新）
    private List<SimQuestion> SimQuestions;//相似问题
    private List<Solution> Solutions;//⽂本型知识答案


}
