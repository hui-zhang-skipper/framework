package com.hui.zhang.client.dquartz;

import com.hui.zhang.dquartz.DistributedContext;
import com.hui.zhang.dquartz.executor.DistributedExecutor;

/**
 * @author zhanghui32
 * @date 2017/2/23
 */
public abstract class CommonDistributedExecutor implements DistributedExecutor {
    public boolean isGroupMode() {
        return true;
    }

    public String getLocalId() {
        return "127.0.0.1";
    }

    public void postProcess(DistributedContext distributedContext) {
        DistributedContextHelper.addDistributedContext(distributedContext);
    }

    public void takeLeadership(DistributedContext distributedContext) {

    }

    public void notExecute(DistributedContext distributedContext) {

    }
}
