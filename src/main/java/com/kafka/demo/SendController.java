package com.kafka.demo;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SendController {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/simpleMsg")
    public String sendSimple() {
        ListenableFuture send = kafkaTemplate.send("simpleMsg", 1,"test", "This is a async simple Message");
        send.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("发送成功");
            }
        });
        return "success";
    }

    @RequestMapping("/syncSimpleMsg")
    public String syncSendSimple() {
        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("syncSimpleMsg", "This is a sync message");
        try {
            kafkaTemplate.send(producerRecord).get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("发送失败");
            e.printStackTrace();
        }

        return "success";
    }

    @RequestMapping("/modalMsg")
    public String modalMsg() {
        TestModal testModal = new TestModal();
        testModal.setId(1);
        testModal.setText("one");
        ListenableFuture send = kafkaTemplate.send("modalMsg", Util.toByteArray(testModal).toString());
        send.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送失败");
            }

            @Override
            public void onSuccess(Object o) {
                System.out.println("发送成功");
            }
        });
        return "success";
    }
}
