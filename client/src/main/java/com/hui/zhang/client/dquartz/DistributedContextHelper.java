package com.hui.zhang.client.dquartz;

import com.hui.zhang.dquartz.DistributedContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghui32
 * @date 2017/2/23
 * 监听spring事件，当容器初始化完成，获取所有分布式服务
 */
public class DistributedContextHelper {
    private final static Map<String, DistributedContext> distributedContextMap = new HashMap<String, DistributedContext>();

    public static void addDistributedContext(DistributedContext distributedContext) {
        distributedContextMap.put(distributedContext.getTargetName(), distributedContext);
    }

    public static Collection<DistributedContext> getDistributedContexts() {
        return distributedContextMap.values();
    }
}
