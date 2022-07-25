package com.example.demo;

import com.example.demo.Async.Service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@SpringBootTest
@AllArgsConstructor
@Controller
public class TestController {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() {
        System.out.println("TEST ASYNC");
        try {
            for (int i = 0; i < 50; i++) {
                asyncService.asyncMethod(i);
            }
        } catch(TaskRejectedException e){
                e.printStackTrace();
        }
    }
}