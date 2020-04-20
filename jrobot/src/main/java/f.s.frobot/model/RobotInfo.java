package f.s.frobot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RobotInfo {
    private Long id;

    private Byte type;

    private String name;

    private String robotImg;

    private String aiImg;

    private String welcome;

    private String h5Url;

    private Byte status;

    private String instanceId;

    private String languageCode;

    private String timeZone;

    private String avatar;

    private Date indate;

    private Integer baseUserId;

    private String baseUserName;

    private Date btime;

    private Date ctime;

    private Date utime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRobotImg() {
        return robotImg;
    }

    public void setRobotImg(String robotImg) {
        this.robotImg = robotImg;
    }

    public String getAiImg() {
        return aiImg;
    }

    public void setAiImg(String aiImg) {
        this.aiImg = aiImg;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Integer getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Integer baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getBaseUserName() {
        return baseUserName;
    }

    public void setBaseUserName(String baseUserName) {
        this.baseUserName = baseUserName;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public RobotInfo selective(Column ... columns) {
        this.selectiveColumns.clear();
        if (columns != null) {
            for (Column column : columns) {
                this.selectiveColumns.put(column.value(), true);
            }
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private RobotInfo obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new RobotInfo();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.id
         *
         * @param id the value for robot_info.id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.type
         *
         * @param type the value for robot_info.type
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder type(Byte type) {
            obj.setType(type);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.name
         *
         * @param name the value for robot_info.name
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.robot_img
         *
         * @param robotImg the value for robot_info.robot_img
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder robotImg(String robotImg) {
            obj.setRobotImg(robotImg);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.ai_img
         *
         * @param aiImg the value for robot_info.ai_img
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder aiImg(String aiImg) {
            obj.setAiImg(aiImg);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.welcome
         *
         * @param welcome the value for robot_info.welcome
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder welcome(String welcome) {
            obj.setWelcome(welcome);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.h5_url
         *
         * @param h5Url the value for robot_info.h5_url
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder h5Url(String h5Url) {
            obj.setH5Url(h5Url);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.status
         *
         * @param status the value for robot_info.status
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.instance_id
         *
         * @param instanceId the value for robot_info.instance_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder instanceId(String instanceId) {
            obj.setInstanceId(instanceId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.language_code
         *
         * @param languageCode the value for robot_info.language_code
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder languageCode(String languageCode) {
            obj.setLanguageCode(languageCode);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.time_zone
         *
         * @param timeZone the value for robot_info.time_zone
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder timeZone(String timeZone) {
            obj.setTimeZone(timeZone);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.avatar
         *
         * @param avatar the value for robot_info.avatar
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder avatar(String avatar) {
            obj.setAvatar(avatar);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.indate
         *
         * @param indate the value for robot_info.indate
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder indate(Date indate) {
            obj.setIndate(indate);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.base_user_id
         *
         * @param baseUserId the value for robot_info.base_user_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder baseUserId(Integer baseUserId) {
            obj.setBaseUserId(baseUserId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.base_user_name
         *
         * @param baseUserName the value for robot_info.base_user_name
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder baseUserName(String baseUserName) {
            obj.setBaseUserName(baseUserName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.btime
         *
         * @param btime the value for robot_info.btime
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder btime(Date btime) {
            obj.setBtime(btime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.ctime
         *
         * @param ctime the value for robot_info.ctime
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder ctime(Date ctime) {
            obj.setCtime(ctime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column robot_info.utime
         *
         * @param utime the value for robot_info.utime
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder utime(Date utime) {
            obj.setUtime(utime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public RobotInfo build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table robot_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        type("type"),
        name("name"),
        robotImg("robot_img"),
        aiImg("ai_img"),
        welcome("welcome"),
        h5Url("h5_url"),
        status("status"),
        instanceId("instance_id"),
        languageCode("language_code"),
        timeZone("time_zone"),
        avatar("avatar"),
        indate("indate"),
        baseUserId("base_user_id"),
        baseUserName("base_user_name"),
        btime("btime"),
        ctime("ctime"),
        utime("utime");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table robot_info
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}