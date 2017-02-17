package com.hui.zhang.jingbean.domain;

/**
 * @author zhanghui243
 * @date 2016-11-25
 * @description 接口服务返回信息
 */
public class JingBeanResponse<T> {
    /**
     * 京豆jsf接口返回代码
     */
    private String code;
    /**
     * 京豆jsf接口返回内容
     */
    private String msg;
    /**
     * 京豆服务处理过的返回结果
     * 京豆余额返回Integer 表示京豆额
     * 京豆消费和收入接口返回Boolean 表示结果
     */
    private T result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
