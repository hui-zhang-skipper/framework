package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 调用支付接口请求参数信息
 */
public class JingBeanRequest extends JingBeanBase {
    /**
     * 京豆配置密钥
     */
    private String key;
    /**
     * 京豆池业务编号
     */
    private String businessId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
