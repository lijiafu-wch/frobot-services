package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.SynonymService;
import f.s.frobot.model.aliyun.DescribeCoreWord;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *  核心词
 * @author lijiafu
 * @date 2020/2/28 15:25
 * @since 1.0
 */
@Service
@Slf4j
public class SynonymServiceImpl implements SynonymService {
    @Autowired
    private BotService botService;
    @Override
    public List<String> list(String coreWordName) throws Exception {
        DescribeCoreWord describeCoreWord = botService.describeCoreWord(coreWordName);
        return describeCoreWord.getSynonyms();
    }

    /**
     * 同义词添加
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    @Override
    public void add(String coreWordName, String synonym) throws Exception {
        botService.addSynonym(coreWordName, synonym);
    }

    /**
     * 同义词删除
     * @author lijiafu
     * @date 2020/2/28 15:23
     */
    @Override
    public void delete(String coreWordName, String synonym) throws Exception {
        botService.removeSynonym(coreWordName, synonym);
    }
}
