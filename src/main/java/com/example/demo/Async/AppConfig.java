package com.example.demo.Async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AppConfig extends AsyncConfigurerSupport {
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); //기본 실행 대기하는 Thread의 수
        executor.setMaxPoolSize(30); //동시 동작하는 최대 Thread의 수
        executor.setQueueCapacity(50); //MaxPoolSize 초과 요청에서 Thread 생성 요청시,해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작
        executor.setThreadNamePrefix("ASYNC-"); //생성되는 Thread 접두사 지정
        executor.initialize();
        return executor;
    }
}
