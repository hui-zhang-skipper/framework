package com.hui.zhang.dquartz.executor;

import com.hui.zhang.dquartz.DistributedContext;

/**
 * @author zhanghui32
 * @date 2017/2/21
 */
public interface DistributedExecutor {
    /**
     * 获取当前分布式work是否是分组运行模式
     *
     * @return
     */
    public boolean isGroupMode();

    /**
     * 获取本地唯一标示
     *
     * @return
     */
    public String getLocalId();

    /**
     * 分布式机器初始化完成后的后处理扩展方法
     *
     * @param distributedContext
     */
    public void postProcess(final DistributedContext distributedContext);

    /**
     * 获取leader之后的回调方法
     */
    public void takeLeadership(final DistributedContext distributedContext);

    /**
     * 分布式分组运行模式下，如果该机器没有权限执行业务逻辑，会调用此方法
     */
    public void notExecute(final DistributedContext distributedContext);
}
