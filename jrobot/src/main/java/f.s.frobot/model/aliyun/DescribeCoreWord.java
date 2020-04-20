package f.s.frobot.model.aliyun;

import java.util.List;

import lombok.Data;

/**
 * 核心词详情
 * @author lijiafu
 * @date 2020/2/29 11:14
 * @since 1.0
 */
@Data
public class DescribeCoreWord {

    private String CoreWordCode;// 唯⼀编码
    private String CoreWordName;// 名称
    private List<String> Synonyms;//同义词列表
    private String ModifyTime; //修改时间 (UTC 时间)
    private String CreateTime; //创建时间 (UTC 时间)
}
