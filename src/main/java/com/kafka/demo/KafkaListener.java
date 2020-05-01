package com.kafka.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @org.springframework.kafka.annotation.KafkaListener(topicPartitions = {@TopicPartition(topic = "simpleMsg", partitions = {"1"})})
    public void receiveSimpleMsg(String msg) {
        System.out.println("收到消息 " + groupId + " " + msg);
    }


    @org.springframework.kafka.annotation.KafkaListener(topics = {"modalMsg"}, groupId = "test")
    public void receiveSimpleMsg(byte[] bytes) {
        System.out.println("收到消息 " + Util.toObject(bytes, TestModal.class).toString());
    }


    @org.springframework.kafka.annotation.KafkaListener(topics = {"syncSimpleMsg"}, groupId = "test")
    public void receiveSyncSimpleMsg(String msg) {
        System.out.println("收到消息 " + msg);
    }
}
