package com.hui.zhang.client;

import com.hui.zhang.jingbean.domain.JingBeanRequest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author zhanghui243
 * @date 2016-11-28
 * @description j路由管理
 */
@Component
public class JingBeanCommonRoute implements BeanFactoryAware {


    private BeanFactory beanFactory;

    public JingBeanRequest selectJingBeanPool(int gameId) {
        return beanFactory.getBean(getPoolKey(gameId), JingBeanRequest.class);
    }

    private String getPoolKey(int gameId) {
        return "jingbeanRequestNormal" + "_" + gameId;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
