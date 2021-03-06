package f.s.frobot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CallThirdConfig {
    private Integer id;

    private Integer baseUserId;

    private String name;

    private String k;

    private String v;

    private Byte status;

    private String remark;

    private Date ctime;

    private Date utime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    private Map<String, Boolean> selectiveColumns = new HashMap<String, Boolean>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(Integer baseUserId) {
        this.baseUserId = baseUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
     * This method corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public CallThirdConfig selective(Column ... columns) {
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
     * This class corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private CallThirdConfig obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new CallThirdConfig();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_third_config.id
         *
         * @param id the value for call_third_config.id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_third_config.base_user_id
         *
         * @param baseUserId the value for call_third_config.base_user_id
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
         * This method sets the value of the database column call_third_config.name
         *
         * @param name the value for call_third_config.name
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
         * This method sets the value of the database column call_third_config.k
         *
         * @param k the value for call_third_config.k
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder k(String k) {
            obj.setK(k);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_third_config.v
         *
         * @param v the value for call_third_config.v
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder v(String v) {
            obj.setV(v);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_third_config.status
         *
         * @param status the value for call_third_config.status
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
         * This method sets the value of the database column call_third_config.remark
         *
         * @param remark the value for call_third_config.remark
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_third_config.ctime
         *
         * @param ctime the value for call_third_config.ctime
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
         * This method sets the value of the database column call_third_config.utime
         *
         * @param utime the value for call_third_config.utime
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
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public CallThirdConfig build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table call_third_config
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        baseUserId("base_user_id"),
        name("name"),
        k("k"),
        v("v"),
        status("status"),
        remark("remark"),
        ctime("ctime"),
        utime("utime");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_third_config
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}