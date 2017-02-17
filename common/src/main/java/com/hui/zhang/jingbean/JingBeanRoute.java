package com.hui.zhang.jingbean;

import com.hui.zhang.jingbean.domain.JingBeanGroupEnum;
import com.hui.zhang.jingbean.rpc.JingBeanJsfFacade;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 支付机房路由接口
 */
public interface JingBeanRoute {
    /**
     * 根据机房分组别名 获取调用接口
     *
     * @param jsfAlias jsf接口别名
     * @return 支付接口
     */
    JingBeanJsfFacade dynamicFacadeGroup(JingBeanGroupEnum jsfAlias);
}
