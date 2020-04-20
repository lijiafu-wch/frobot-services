package f.s.fadminrobot.service.impl;

import f.s.fadminrobot.service.BotService;
import f.s.fadminrobot.service.CategoryService;
import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.ResponseResult;
import f.s.frobot.vo.CategoryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *  知识类目实现类
 * @author lijiafu
 * @date 2020/2/26 11:41
 * @since 1.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private BotService botService;

    /**
     * 知识类目列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Categories list(CategoryVo categoryVo) throws Exception {
        Categories categories = botService.queryCategories(categoryVo.getParentCategoryId(),categoryVo.isShowChildrens());
        return categories;
    }

    /**
     * 知识类目详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Category info(CategoryVo categoryVo) throws Exception {
        Category category = botService.describeCategory(categoryVo.getCategoryId());
        return category;
    }

    /**
     * 创建知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public Long add(CategoryVo categoryVo) throws Exception {
        ResponseResult responseResult = botService.createCategory(categoryVo.getParentCategoryId(),categoryVo.getName());
        return responseResult.getCategoryId();
    }

    /**
     * 修改知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void edit(CategoryVo categoryVo) throws Exception {
        botService.updateCategory(categoryVo.getCategoryId(),categoryVo.getName());
    }

    /**
     * 删除知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @Override
    public void delete(CategoryVo categoryVo) throws Exception {
        botService.deleteCategory(categoryVo.getCategoryId());
    }
}
