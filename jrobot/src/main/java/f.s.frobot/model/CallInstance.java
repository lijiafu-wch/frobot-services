package f.s.frobot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CallInstance {
    private Long id;

    private String instanceId;

    private String instanceName;

    private String groupId;

    private String groupName;

    private String scriptId;

    private Integer baseUserId;

    private Integer callingNum;

    private Integer calledNum;

    private Integer callNumPrefix;

    private Byte status;

    private String strategy;

    private Date ctime;

    private Date utime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table call_instance
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

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    public Integer getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Integer baseUserId) {
        this.baseUserId = baseUserId;
    }

    public Integer getCallingNum() {
        return callingNum;
    }

    public void setCallingNum(Integer callingNum) {
        this.callingNum = callingNum;
    }

    public Integer getCalledNum() {
        return calledNum;
    }

    public void setCalledNum(Integer calledNum) {
        this.calledNum = calledNum;
    }

    public Integer getCallNumPrefix() {
        return callNumPrefix;
    }

    public void setCallNumPrefix(Integer callNumPrefix) {
        this.callNumPrefix = callNumPrefix;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
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
     * This method corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public CallInstance selective(Column ... columns) {
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
     * This class corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private CallInstance obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new CallInstance();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.id
         *
         * @param id the value for call_instance.id
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
         * This method sets the value of the database column call_instance.instance_id
         *
         * @param instanceId the value for call_instance.instance_id
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
         * This method sets the value of the database column call_instance.instance_name
         *
         * @param instanceName the value for call_instance.instance_name
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder instanceName(String instanceName) {
            obj.setInstanceName(instanceName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.group_id
         *
         * @param groupId the value for call_instance.group_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder groupId(String groupId) {
            obj.setGroupId(groupId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.group_name
         *
         * @param groupName the value for call_instance.group_name
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder groupName(String groupName) {
            obj.setGroupName(groupName);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.script_Id
         *
         * @param scriptId the value for call_instance.script_Id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder scriptId(String scriptId) {
            obj.setScriptId(scriptId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.base_user_id
         *
         * @param baseUserId the value for call_instance.base_user_id
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
         * This method sets the value of the database column call_instance.calling_num
         *
         * @param callingNum the value for call_instance.calling_num
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder callingNum(Integer callingNum) {
            obj.setCallingNum(callingNum);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.called_num
         *
         * @param calledNum the value for call_instance.called_num
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder calledNum(Integer calledNum) {
            obj.setCalledNum(calledNum);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.call_num_prefix
         *
         * @param callNumPrefix the value for call_instance.call_num_prefix
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder callNumPrefix(Integer callNumPrefix) {
            obj.setCallNumPrefix(callNumPrefix);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.status
         *
         * @param status the value for call_instance.status
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
         * This method sets the value of the database column call_instance.strategy
         *
         * @param strategy the value for call_instance.strategy
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder strategy(String strategy) {
            obj.setStrategy(strategy);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_instance.ctime
         *
         * @param ctime the value for call_instance.ctime
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
         * This method sets the value of the database column call_instance.utime
         *
         * @param utime the value for call_instance.utime
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
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public CallInstance build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table call_instance
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        instanceId("instance_id"),
        instanceName("instance_name"),
        groupId("group_id"),
        groupName("group_name"),
        scriptId("script_Id"),
        baseUserId("base_user_id"),
        callingNum("calling_num"),
        calledNum("called_num"),
        callNumPrefix("call_num_prefix"),
        status("status"),
        strategy("strategy"),
        ctime("ctime"),
        utime("utime");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_instance
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}