package f.s.frobot.model.aliyun;

import lombok.Data;

/**
 *  视角
 * @author lijiafu
 * @date 2020/2/26 15:44
 * @since 1.0
 */
@Data
public class Perspective {
    private String PerspectiveId;//视⻆主键（code_id）
    private String PerspectiveCode;//视⻆编码（⽤于问答api）
    private String ModifyTime;//修改时间 UTC时间
    private String CreateTime;//创建时间 UTC时间
    private Boolean SelfDefine;//是否⾃定义
    private Integer Status;//数据状态：3：选中；1：未选中
    private String Name;//视⻆名称
}
