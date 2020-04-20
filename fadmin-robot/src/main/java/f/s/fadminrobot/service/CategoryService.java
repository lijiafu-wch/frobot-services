package f.s.fadminrobot.service;

import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.model.aliyun.QueryBots;
import f.s.frobot.vo.CategoryVo;

/**
 *  知识类目接口
 * @author lijiafu
 * @date 2020/2/18 16:27
 * @since 1.0
 */
public interface CategoryService {

    /**
     * 知识类目列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Categories list(CategoryVo categoryVo) throws Exception;

    /**
     * 知识类目详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Category info(CategoryVo categoryVo) throws Exception;
    /**
     * 创建知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    Long add(CategoryVo categoryVo) throws Exception;
    /**
     * 修改知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void edit(CategoryVo categoryVo) throws Exception;
    /**
     * 删除知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void delete(CategoryVo categoryVo) throws Exception;

}
