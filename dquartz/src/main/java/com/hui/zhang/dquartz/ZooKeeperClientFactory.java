package com.hui.zhang.dquartz;

import com.hui.zhang.dquartz.config.ZooKeeperConfig;
import org.apache.curator.framework.CuratorFramework;

/**
 * @author zhanghui32
 * @date 2017/2/21
 */
public interface ZooKeeperClientFactory {
    public ZooKeeperConfig getZooKeeperConfig();

    public CuratorFramework getCuratorFramework();
}
