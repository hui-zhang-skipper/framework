package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 接口机房组类型
 */
public enum JingBeanGroupEnum {
    LF_JPOS_CLOUD("LF_JPOS_CLOUD", "廊坊"),
    MJQ_JPOS_CLOUD("MJQ_JPOS_CLOUD", "马驹桥"),
    JPOS("JPOS", "测试");
    private String name;
    private String value;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    JingBeanGroupEnum(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public static JingBeanGroupEnum toEnum(String value) {
        for (JingBeanGroupEnum e : JingBeanGroupEnum.values()) {
            if (value.equals(e.getValue().trim())) {
                return e;
            }
        }
        return JingBeanGroupEnum.LF_JPOS_CLOUD;
    }


}
