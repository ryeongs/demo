package com.example.demo.Kafka.Controller;

import com.example.demo.Model.Member;
import com.example.demo.Kafka.Service.KafkaProducer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Kafka"})
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return "success : " + message;
    }

    @PostMapping("/memberInfo")
    public String memberInfo(@RequestBody Member member) {
        if( "".equals(member.getName()) || member.getName() == null ) member.setName("test-name");
        if( "".equals(member.getEmail()) || member.getEmail() == null ) member.setEmail("test@test.com");

        sendMessage(member.toString());

        return member.toString();
    }

}