package com.example.demo.Async.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {
    @Async
    public void asyncMethod(int i) {
        try {
            Thread.sleep(500);
            log.info("[AsyncMethod]"+"-"+i);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
