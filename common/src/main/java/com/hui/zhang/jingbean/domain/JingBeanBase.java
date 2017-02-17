package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 调用京豆支付接口请求参数信息
 */
public class JingBeanBase {
    /**
     * 交易金额
     */
    private Integer amount;
    /**
     * 机构号
     */
    private String orgId;
    /**
     * 一级业务编号
     */
    private String topBusinessId;
    /**
     * 二级业务编号
     */
    private String secondBusinessId;

    /**
     * 交易流水号
     */
    private String sn;
    /**
     * 调用端编号
     */
    private String callerId;

    /**
     * 客户端所携带的用户pin
     */
    private String pin;
    /**
     * 来源系统名
     */
    private String domain;
    /**
     * 用户是否可见
     */
    private String userVisible;
    /**
     * 用户所见方案信息
     */
    private String memo;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTopBusinessId() {
        return topBusinessId;
    }

    public void setTopBusinessId(String topBusinessId) {
        this.topBusinessId = topBusinessId;
    }

    public String getSecondBusinessId() {
        return secondBusinessId;
    }

    public void setSecondBusinessId(String secondBusinessId) {
        this.secondBusinessId = secondBusinessId;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserVisible() {
        return userVisible;
    }

    public void setUserVisible(String userVisible) {
        this.userVisible = userVisible;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}

