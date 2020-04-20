package f.s.fadminrobot.service;

import java.util.List;

/**
 * 核心词
 * @author lijiafu
 * @date 2020/2/28 15:22
 * @since 1.0.0
 */
public interface SynonymService {

    /**
     * 同义词列表
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    List<String> list(String coreWordName) throws Exception;
    /**
     * 同义词添加
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    void add(String coreWordName,String synonym) throws Exception;
    /**
     * 同义词删除
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    void delete(String coreWordName,String synonym) throws Exception;
}
