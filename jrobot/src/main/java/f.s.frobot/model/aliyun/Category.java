package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 *  类目
 * @author lijiafu
 * @date 2020/2/18 21:38
 * @since 1.0
 */
@Data
public class Category {

    private Long CategoryId;//类⽬id
    private Long ParentCategoryId;//⽗类⽬id
    private String Name;//类⽬名称
    private List<Category> Childrens;//⼦节点
}
