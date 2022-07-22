package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {
    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor ThreadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor.setMaxPoolSize(100); //최대 갯수
        ThreadPoolTaskExecutor.setCorePoolSize(10); //코어 사이즈
        ThreadPoolTaskExecutor.setQueueCapacity(10); //큐 사이즈
        ThreadPoolTaskExecutor.setThreadNamePrefix("Async-"); //접두사
        return ThreadPoolTaskExecutor;
    }
    // @Async에 대한 설정이 없으면 새로운 비동기 작업을 스레드 풀에서 처리하는 게 아니라 새로운 스레드를 매번 생성해서 작업을 수행시키는 것이 디폴트 설정
}
