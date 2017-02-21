package com.hui.zhang.dquartz;

import com.hui.zhang.dquartz.config.Group;
import com.hui.zhang.dquartz.executor.DistributedExecutor;

import java.util.List;

/**
 * @author zhanghui32
 * @date 2017/2/21
 */
public class DistributedContext {

    private final String targetName;
    private final String description;
    private final List<Group> groups;
    private final String localId;
    private final int groupIndex;
    private final Group group;

    private DistributedControl distributedControl;
    private DistributedExecutor distributedExecutor;

    public DistributedContext(DistributedControl distributedControl) {
        this.distributedControl = distributedControl;
        this.distributedExecutor = distributedControl.getDistributedExecutor();
        this.targetName = distributedControl.getTargetName();
        this.description = distributedControl.getDescription();
        this.localId = distributedControl.getLocalId();
        this.groups = distributedControl.getGroups();
        this.groupIndex = distributedControl.getGroupIndex();
        this.group = groups.get(groupIndex);
    }

    public String getTargetName() {

        return distributedControl.getTargetName();
    }

    public String getDescription() {
        return distributedControl.getDescription();
    }

    public List<Group> getGroups() {
        return groups;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public Group getGroup() {
        return group;
    }

    public String getLocalId() {
        return localId;
    }

    public DistributedControl getDistributedControl() {
        return distributedControl;
    }

    public DistributedExecutor getDistributedExecutor() {
        return distributedExecutor;
    }
}
