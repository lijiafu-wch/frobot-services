package f.s.frobot.vo;

import lombok.Data;

/**
 * 知识类目vo
 * @author lijiafu
 * @date 2020/2/26 11:32
 * @since 1.0
 */
@Data
public class CategoryVo {

    private Long categoryId;//类目id
    private Long parentCategoryId = -1l;//⽗类⽬id，默认-1，对应根⽬录
    private boolean showChildrens = false;//是否包含⼦节点，默认false
    private String name;//类⽬名称，不超过64字

}
