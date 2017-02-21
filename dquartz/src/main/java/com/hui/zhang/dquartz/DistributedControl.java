package com.hui.zhang.dquartz;

import com.hui.zhang.dquartz.config.Group;
import com.hui.zhang.dquartz.executor.DistributedExecutor;

import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/2/21
 */
public interface DistributedControl {
    public DistributedExecutor getDistributedExecutor();

    public String getTargetName();

    public String getDescription();

    public List<Group> getGroups();

    public String getLocalId();

    public int getGroupIndex();
}
