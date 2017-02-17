package com.hui.zhang.jingbean;

import com.hui.zhang.jingbean.domain.JingBeanResponse;
import com.hui.zhang.jingbean.rpc.Result;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 支付回调
 */
public interface JingBeanCallback {
    /**
     * 京豆支付结果回调处理
     *
     * @param result 处理返回结果
     * @param args   方法类型，appName，交易流水
     * @param <T>
     * @return
     */
    <T> JingBeanResponse doResultCallback(Result<T> result, String... args);

}