package com.hui.zhang.client.kafka.consumer;

/**
 * @author zhanghui32
 * @date 2017/7/23
 */
public class KafkaTopicPartitionInfo {

    //主题
    public final String topic;
    //分区号
    public final int partitionID;

    public KafkaTopicPartitionInfo(String topic, int partitionID) {
        this.partitionID = partitionID;
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KafkaTopicPartitionInfo that = (KafkaTopicPartitionInfo) o;

        if (partitionID != that.partitionID) return false;
        return topic != null ? topic.equals(that.topic) : that.topic == null;
    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + partitionID;
        return result;
    }
}
