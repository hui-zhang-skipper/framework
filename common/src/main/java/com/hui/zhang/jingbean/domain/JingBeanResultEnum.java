package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 调用支付接口返回Code，Msg
 */
public enum JingBeanResultEnum {
    SUCCESS("0000", "请求成功"),
    SIGNATURE_FAIL("0002", "签名失败的错误代码"),
    BALANCE_LESS("0003", "用户余额不足错误代码"),
    REPEAT_TRADE("0004", "重复交易错误代码"),
    PARAM_ILLEGAL("0006", "参数非法错误代码"),
    JPEAS_BLAMING("0010", "超发"),
    PARAM_ERROR("00010", "参数异常"),
    INVOKE_ERROR("00011", "服务调用异常"),
    ACTIVITY_END("00012", "活动已结束"),
    ACTIVITY_NOT_EXISTS("00013", "活动不存在"),
    EXCEED_RISK_LEVEL("00014", "超出风控级别"),
    SYSTEM_EXCEPTION("10000", "系统异常");


    JingBeanResultEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
