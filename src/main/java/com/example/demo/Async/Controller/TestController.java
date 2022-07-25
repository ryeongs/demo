package com.example.demo.Async.Controller;

import com.example.demo.Async.Service.AsyncService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@AllArgsConstructor
@Controller
public class TestController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/testAsync")
    public String testAsync() {
        log.info("TEST ASYNC");
        try {
            for (int i = 0; i < 50; i++) {
                asyncService.asyncMethod(i);
            }
        } catch(TaskRejectedException e){
                e.printStackTrace();
        }
        return "";
    }
}