package com.hui.zhang.jingbean.rpc;

import java.io.Serializable;

/**
 * @author zhanghui32
 * @date 2017/2/17
 * 模拟rpc 返回结果
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 3801815424835939117L;
    private String code;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String msg;
    private T data;
}
