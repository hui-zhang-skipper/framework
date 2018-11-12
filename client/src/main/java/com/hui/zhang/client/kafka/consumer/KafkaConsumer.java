package com.hui.zhang.client.kafka.consumer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.ws.handler.MessageContext;
import java.util.Collections;
import java.util.Properties;


@Component
public class KafkaConsumer implements Runnable {

    @Value("${kafka.server:test}")
    private String kafkaServer;

    @Value("${kafka.topic:test}")
    private String kafkaTopic;


    @Override
    public void run() {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServer);
        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");
        props.put("max.poll.records", 10);
        props.put("session.timeout.ms", "30000");
        props.put("group.id", "data_quota");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(kafkaTopic));
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    MessageContext messageContext = JSON.parseObject(record.value(), MessageContext.class);

                    consumer.commitSync();
                }
            } catch (Exception e) {

            }
        }
    }
}