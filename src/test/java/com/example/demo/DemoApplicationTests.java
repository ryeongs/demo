package com.example.demo;

import com.example.demo.Kafka.Service.KafkaProducer;
import com.example.demo.Model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    private final KafkaProducer producer;

    @Autowired
    DemoApplicationTests(KafkaProducer producer) {
        this.producer = producer;
    }

    @Test
    public void memberInfo() {
        Member member = new Member();
        if( "".equals(member.getName()) || member.getName() == null ) member.setName("test-name");
        if( "".equals(member.getEmail()) || member.getEmail() == null ) member.setEmail("test@test.com");

        producer.sendMessage(member.toString());
        System.out.println(member.toString());
    }

}
