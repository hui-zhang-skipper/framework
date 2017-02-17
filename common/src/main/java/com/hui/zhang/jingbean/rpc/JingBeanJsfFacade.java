package com.hui.zhang.jingbean.rpc;

/**
 * @author zhanghui32
 * @date 2017/2/17
 * 模拟rpc接口实现接口
 */
public interface JingBeanJsfFacade {
    Result<String> incomeBeans(JPeasRequest request);

    Result<String> consumeBeans(JPeasRequest request);

    Result<Integer> balanceBeans(JPeasRequest request);

}
