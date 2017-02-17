package com.hui.zhang.jingbean.impl;

import com.alibaba.fastjson.JSON;
import com.hui.zhang.jingbean.JingBeanCallback;
import com.hui.zhang.jingbean.domain.JingBeanMethodEnum;
import com.hui.zhang.jingbean.domain.JingBeanResponse;
import com.hui.zhang.jingbean.domain.JingBeanResultEnum;
import com.hui.zhang.jingbean.profiler.Profiler;
import com.hui.zhang.jingbean.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 京豆支付回调接口默认实现
 */
public class DefaultJingBeanCallback implements JingBeanCallback {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultJingBeanCallback.class);

    /**
     * @param result 接口返回值
     * @param args   方法类型：扣豆，发豆，余额；应用appName；交易流水
     * @param <T>
     * @return
     */
    public <T> JingBeanResponse doResultCallback(Result<T> result, String... args) {
        JingBeanResponse response = new JingBeanResponse();
        response.setCode(result.getCode());
        response.setMsg(result.getMsg());
        LOGGER.info("doResultCallback the result={},sn={}", JSON.toJSONString(result), args[2]);
        if (null == result || null == result.getData()) {
            LOGGER.error("the result data is null! ");
            setResponse(args[0], -1, response, false);
            return response;
        }
        //用户余额不足
        if (JingBeanResultEnum.BALANCE_LESS.getKey().equals(result.getCode())) {
            LOGGER.error("consumeBeans result code is error! code:{},msg:{}", result.getCode(), result.getMsg());
            setResponse(args[0], -1, response, false);
            return response;
        }
        //重复交易或交易成功
        if (JingBeanResultEnum.SUCCESS.getKey().equals(result.getCode()) || JingBeanResultEnum.REPEAT_TRADE.getKey().equals(result.getCode())) {
            setResponse(args[0], result.getData(), response, true);
            return response;
        }
        //京豆超发 报警
        if (JingBeanResultEnum.JPEAS_BLAMING.getKey().equals(result.getCode())) {
            Profiler.businessAlarm(args[1] + ".task.jingBeansExceedPoolBalance", new Date().getTime(), String.format("the balance is insufficient,sn:s%", args[2]));
            LOGGER.error("incomeBeans result code is error! code:{}, bettingRecordId:{}", result.getCode(), args[2]);
        }
        setResponse(args[0], -1, response, false);
        return response;
    }

    /**
     * 设置回调返回值
     *
     * @param method   方法类型
     * @param data     原始返回数据
     * @param response 返回对象
     * @param result   处理返回数据
     */
    private void setResponse(String method, Object data, JingBeanResponse response, Object result) {
        if (JingBeanMethodEnum.BALANCEBEANS.getValue().equals(method)) {
            response.setResult(data);
        } else {
            response.setResult(result);
        }
    }
}
