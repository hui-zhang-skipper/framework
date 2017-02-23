package com.hui.zhang.client.dquartz;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

/**
 * @author zhanghui32
 * @date 2017/2/23
 * zookeeper 状态监听
 */
public class DefaultConnectionStateListener implements ConnectionStateListener {

    public void stateChanged(CuratorFramework curatorFramework, ConnectionState newState) {
        switch (newState) {
            case CONNECTED:
                connected(curatorFramework);
                break;
            case SUSPENDED:
                suspended(curatorFramework);
                break;
            case LOST:
                lost(curatorFramework);
                break;
            case RECONNECTED:
                reconnected(curatorFramework);
                break;
            case READ_ONLY:
                readOnly(curatorFramework);
                break;
            default:
                break;
        }
    }

    public void connected(CuratorFramework curatorFramework) {
    }

    public void suspended(CuratorFramework curatorFramework) {

    }

    public void reconnected(CuratorFramework curatorFramework) {

    }

    public void lost(CuratorFramework curatorFramework) {

    }

    public void readOnly(CuratorFramework curatorFramework) {

    }
}
