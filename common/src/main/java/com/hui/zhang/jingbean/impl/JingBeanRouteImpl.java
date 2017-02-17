package com.hui.zhang.jingbean.impl;

import com.hui.zhang.jingbean.JingBeanRoute;
import com.hui.zhang.jingbean.domain.JingBeanGroupEnum;
import com.hui.zhang.jingbean.rpc.JingBeanJsfFacade;

import java.util.Map;

/**
 * @author zhanghui243
 * @date 2016-11-24
 * @description 支付机房路由实现
 */
public class JingBeanRouteImpl implements JingBeanRoute {

    private Map<JingBeanGroupEnum, JingBeanJsfFacade> jingBeanJsfFacadeMap;

    public Map<JingBeanGroupEnum, JingBeanJsfFacade> getJingBeanJsfFacadeMap() {
        return jingBeanJsfFacadeMap;
    }

    public void setJingBeanJsfFacadeMap(Map<JingBeanGroupEnum, JingBeanJsfFacade> jingBeanJsfFacadeMap) {
        this.jingBeanJsfFacadeMap = jingBeanJsfFacadeMap;
    }

    /**
     * 动态获取jsf 机房接口
     *
     * @param jsfAlias jsf接口别名
     * @return
     */
    public JingBeanJsfFacade dynamicFacadeGroup(JingBeanGroupEnum jsfAlias) {

        if (jsfAlias != null) {
            return jingBeanJsfFacadeMap.get(jsfAlias);
        }
        return jingBeanJsfFacadeMap.get(JingBeanGroupEnum.LF_JPOS_CLOUD);
    }
}
