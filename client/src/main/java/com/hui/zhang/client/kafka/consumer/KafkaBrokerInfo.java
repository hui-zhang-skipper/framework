package com.hui.zhang.client.kafka.consumer;

/**
 * @author zhanghui32
 * @date 2017/7/23
 */
public class KafkaBrokerInfo {

    //主机名
    public final String brokerHost;
    //端口号
    public final int brokerPort;

    public KafkaBrokerInfo(String brokerHost, int brokerPort) {
        this.brokerHost = brokerHost;
        this.brokerPort = brokerPort;
    }

    public KafkaBrokerInfo(String brokerHost) {
        this.brokerHost = brokerHost;
        this.brokerPort = 9092;
    }
}
