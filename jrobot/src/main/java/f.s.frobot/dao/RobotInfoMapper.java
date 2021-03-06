package f.s.frobot.dao;

import f.s.frobot.model.RobotInfo;
import f.s.frobot.model.RobotInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RobotInfoMapper {
    long countByExample(RobotInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RobotInfo record);

    int insertSelective(RobotInfo record);

    List<RobotInfo> selectByExample(RobotInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<RobotInfo> selectByExampleSelective(@Param("example") RobotInfoExample example, @Param("selective") RobotInfo.Column... selective);

    RobotInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    RobotInfo selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RobotInfo.Column... selective);

    int updateByExampleSelective(@Param("record") RobotInfo record, @Param("example") RobotInfoExample example);

    int updateByExample(@Param("record") RobotInfo record, @Param("example") RobotInfoExample example);

    int updateByPrimaryKeySelective(RobotInfo record);

    int updateByPrimaryKey(RobotInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    RobotInfo selectOneByExample(RobotInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    RobotInfo selectOneByExampleSelective(@Param("example") RobotInfoExample example, @Param("selective") RobotInfo.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<RobotInfo> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<RobotInfo> list, @Param("selective") RobotInfo.Column... selective);
}