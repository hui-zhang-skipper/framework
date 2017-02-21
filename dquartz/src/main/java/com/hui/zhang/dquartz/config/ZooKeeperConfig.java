package com.hui.zhang.dquartz.config;

/**
 * @author zhanghui32
 * @date 2017/2/21
 * zookeeper配置
 */
public class ZooKeeperConfig {
    private String connectString;
    private String rootPath;

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String toString() {
        return "ZooKeeperConfig{" +
                "connectString='" + connectString + '\'' +
                ", rootPath='" + rootPath + '\'' +
                '}';
    }
}
