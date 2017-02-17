package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 接口方法类型
 */
public enum JingBeanMethodEnum {

    INCOMEBEANS("incomeBeans", "发豆"),
    BALANCEBEANS("balanceBeans", "余额"),
    CONSUMEBEANS("consumeBeans", "扣豆");
    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    JingBeanMethodEnum(String value, String name) {
        this.name = name;
        this.value = value;
    }
}
