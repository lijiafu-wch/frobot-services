package f.s.fadminrobot.controller;

import f.s.fadminrobot.service.CategoryService;
import f.s.fadminrobot.util.BaseUtil;
import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.vo.CategoryVo;
import f.s.utils.ResultModel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 知识类目controller
 * @author lijiafu
 * @date 2020/2/26 12:00
 * @since 1.0
 */
@RestController
@RequestMapping("admin/robot/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BaseUtil baseUtil;

    /**
     * 知识类目列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/list")
    public ResultModel list(CategoryVo categoryVo) throws Exception{
        Categories Categories = categoryService.list(categoryVo);
        return ResultModel.success(Categories.getCategories());
    }

    /**
     * 知识类目详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/info")
    public ResultModel info(CategoryVo categoryVo) throws Exception{
        Category info = categoryService.info(categoryVo);
        return ResultModel.success(info);
    }
    /**
     * 创建知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel add(CategoryVo categoryVo) throws Exception{
        Long categoryId = categoryService.add(categoryVo);
        return ResultModel.success(categoryId);
    }
    /**
     * 修改知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/edit")
    public ResultModel edit(CategoryVo categoryVo) throws Exception{
       categoryService.edit(categoryVo);
       return ResultModel.success();
    }
    /**
     * 删除知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/delete")
    public ResultModel delete(CategoryVo categoryVo) throws Exception{
        categoryService.delete(categoryVo);
        return ResultModel.success();
    }
}
