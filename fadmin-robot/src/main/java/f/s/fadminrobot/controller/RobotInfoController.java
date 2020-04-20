package f.s.fadminrobot.controller;

import f.s.fadminrobot.error.FrobotErrors;
import f.s.fadminrobot.service.RobotInfoService;
import f.s.frobot.model.RobotInfo;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.vo.PageVo;
import f.s.frobot.vo.RobotInfoVo;
import f.s.jerror.BaseError;
import f.s.utils.ResultModel;
import f.s.utils.Validator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户机器人信息
 * @author lijiafu
 * @date 2020/2/19 10:40
 * @since 1.0
 */
@RestController
@RequestMapping("admin/robot")
public class RobotInfoController extends BaseController{
    @Autowired
    private RobotInfoService robotInfoService;
    @Autowired
    private FrobotErrors fadminRobotErrors;
    /**
     * 获取机器⼈list
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/list")
    public ResultModel list(RobotInfoVo robotInfoVo, PageVo pageVo) throws BaseError {
        PageVo page = robotInfoService.list(robotInfoVo, pageVo);
        return ResultModel.success(page);
    }

    /**
     * 创建机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/add")
    public ResultModel add(RobotInfoVo robotInfoVo) throws Exception {
        Validator.lenBetween(robotInfoVo.getName(),1,7,fadminRobotErrors.RobotNameLengthError());
        robotInfoService.add(robotInfoVo);
        return ResultModel.success();
    }

    /**
     * 编辑机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/edit")
    public ResultModel edit(RobotInfoVo robotInfoVo) throws Exception {
        Validator.lenBetween(robotInfoVo.getName(),1,7,fadminRobotErrors.RobotNameLengthError());
        robotInfoService.edit(robotInfoVo);
        return ResultModel.success();
    }

    /**
     * 查看机器⼈信息
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/info")
    public ResultModel info(Long id) throws Exception {
        RobotInfo robotInfo = robotInfoService.info(id);
        robotInfo.setInstanceId(null);
        return ResultModel.success(robotInfo);
    }

    /**
     * 查看机器⼈绑定类目列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/categories")
    public ResultModel categoriesByRobot(Long id) throws Exception {
        List<Category> categories  = robotInfoService.categoriesByRobot(id);
        return ResultModel.success(categories);
    }

    /**
     * 删除机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @GetMapping("/delete")
    public ResultModel delete(Long id) throws Exception {
       robotInfoService.delete(id);
        return ResultModel.success();
    }

    /**
     * 机器⼈关联商户
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/baseuser/bind")
    public ResultModel bindBaseUser(Long id,int baseUserId,String baseUserName) throws BaseError {
        robotInfoService.bindBaseUser(id, baseUserId,baseUserName);
        return ResultModel.success();
    }

    /**
     * 机器⼈关联知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/categorys/bind")
    public ResultModel bindCategorys(Long id,Long[] categoryIds) throws Exception {
        robotInfoService.bindCategorys(id, categoryIds);
        return ResultModel.success();
    }

    /**
     * 绑定机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    @PostMapping("/bind")
    public ResultModel bindRobot(String[] instanceIds,Integer type) throws Exception {
        //判断机器人是否已存在，存在跳过
        for(String instanceId : instanceIds){
            robotInfoService.bindRobot(instanceId,type);
        }
        return ResultModel.success();
    }
}
