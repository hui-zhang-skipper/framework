package com.hui.zhang.jingbean;

import com.hui.zhang.jingbean.domain.JingBeanGroupEnum;
import com.hui.zhang.jingbean.domain.JingBeanResponse;
import com.hui.zhang.jingbean.domain.JingBeanRequest;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 京豆支付服务
 */
public interface JingBeanService {
    /**
     * 京豆消费
     *
     * @param jingBeanRequest  京豆支付请求参数
     * @param jsfAlias         京豆支付 jsf 接口别名（机房组）
     * @param jingBeanCallback 京豆支付，业务回调接口
     * @return
     */
    JingBeanResponse consumeBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback);

    /**
     * 京豆余额
     *
     * @param jingBeanRequest  京豆支付请求参数
     * @param jsfAlias         京豆支付 jsf 接口别名（机房组）
     * @param jingBeanCallback 京豆支付，业务回调接口
     * @return
     */
    JingBeanResponse balanceBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback);

    /**
     * 京豆收入
     *
     * @param jingBeanRequest  京豆支付请求参数
     * @param jsfAlias         京豆支付 jsf 接口别名（机房组）
     * @param jingBeanCallback 京豆支付，业务回调接口
     * @return
     */
    JingBeanResponse incomeBeans(JingBeanRequest jingBeanRequest, JingBeanGroupEnum jsfAlias, JingBeanCallback jingBeanCallback);
}
