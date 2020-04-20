package f.s.fadminrobot.service;

import f.s.frobot.model.RobotInfo;
import f.s.frobot.model.aliyun.Categories;
import f.s.frobot.model.aliyun.Category;
import f.s.frobot.model.aliyun.DescribeBot;
import f.s.frobot.vo.PageVo;
import f.s.frobot.vo.RobotInfoVo;
import f.s.jerror.BaseError;
import java.util.List;

/**
 *  用户机器人信息接口
 * @author lijiafu
 * @date 2020/2/18 16:27
 * @since 1.0
 */
public interface RobotInfoService {

    /**
     * 获取机器⼈列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    PageVo list(RobotInfoVo robotInfoVo, PageVo pageVo) throws BaseError;

    /**
     * 创建机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void add(RobotInfoVo robotInfoVo) throws Exception;

    /**
     * 编辑机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void edit(RobotInfoVo robotInfoVo) throws Exception;

    /**
     * 查看机器⼈详细信息
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    RobotInfo info(Long id) throws BaseError;

    /**
     * 查看机器⼈绑定类目列表
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    List<Category> categoriesByRobot(Long id) throws Exception;

    /**
     * 删除机器⼈
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void delete(Long id) throws Exception;

    /**
     * 机器⼈关联知识类目
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void bindCategorys(Long id,Long[] categoryIds) throws Exception;

    /**
     * 机器⼈关联商户
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void bindBaseUser(Long id,int baseUserId,String baseUserName) throws BaseError;

    /**
     * 绑定机器人
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    void bindRobot(String instanceId,Integer type) throws Exception;

    /**
     * 阿里机器人详情
     * @author lijiafu
     * @date 2020/2/18 16:30
     */
    DescribeBot describe(String instanceId);

}
