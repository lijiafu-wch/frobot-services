package f.s.frobot.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CallKyOrder {
    private Integer id;

    private String memberNo;

    private String mobile;

    private String name;

    private String rmno;

    private Integer orderId;

    private String memberType;

    private Date morningCallTime;

    private Date arr;

    private Date dep;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table call_ky_order
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

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRmno() {
        return rmno;
    }

    public void setRmno(String rmno) {
        this.rmno = rmno;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Date getMorningCallTime() {
        return morningCallTime;
    }

    public void setMorningCallTime(Date morningCallTime) {
        this.morningCallTime = morningCallTime;
    }

    public Date getArr() {
        return arr;
    }

    public void setArr(Date arr) {
        this.arr = arr;
    }

    public Date getDep() {
        return dep;
    }

    public void setDep(Date dep) {
        this.dep = dep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective() {
        return this.selectiveColumns.size() > 0;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public boolean hasSelective(String column) {
        return this.selectiveColumns.get(column) != null;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public CallKyOrder selective(Column ... columns) {
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
     * This class corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static class Builder {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private CallKyOrder obj;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder() {
            this.obj = new CallKyOrder();
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.id
         *
         * @param id the value for call_ky_order.id
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
         * This method sets the value of the database column call_ky_order.member_no
         *
         * @param memberNo the value for call_ky_order.member_no
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder memberNo(String memberNo) {
            obj.setMemberNo(memberNo);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.mobile
         *
         * @param mobile the value for call_ky_order.mobile
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder mobile(String mobile) {
            obj.setMobile(mobile);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.name
         *
         * @param name the value for call_ky_order.name
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
         * This method sets the value of the database column call_ky_order.rmno
         *
         * @param rmno the value for call_ky_order.rmno
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder rmno(String rmno) {
            obj.setRmno(rmno);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.order_id
         *
         * @param orderId the value for call_ky_order.order_id
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder orderId(Integer orderId) {
            obj.setOrderId(orderId);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.member_type
         *
         * @param memberType the value for call_ky_order.member_type
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder memberType(String memberType) {
            obj.setMemberType(memberType);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.morning_call_time
         *
         * @param morningCallTime the value for call_ky_order.morning_call_time
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder morningCallTime(Date morningCallTime) {
            obj.setMorningCallTime(morningCallTime);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.arr
         *
         * @param arr the value for call_ky_order.arr
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder arr(Date arr) {
            obj.setArr(arr);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method sets the value of the database column call_ky_order.dep
         *
         * @param dep the value for call_ky_order.dep
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Builder dep(Date dep) {
            obj.setDep(dep);
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public CallKyOrder build() {
            return this.obj;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table call_ky_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id"),
        memberNo("member_no"),
        mobile("mobile"),
        name("name"),
        rmno("rmno"),
        orderId("order_id"),
        memberType("member_type"),
        morningCallTime("morning_call_time"),
        arr("arr"),
        dep("dep");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table call_ky_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}