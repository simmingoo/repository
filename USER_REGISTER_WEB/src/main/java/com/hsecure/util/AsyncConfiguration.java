package com.hsecure.util;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration // bean 객체 등록
@EnableAsync // 비동기 프로세서를 사용하겠다고 선언 
public class AsyncConfiguration {
	
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(3);		// Thread SIZE
		taskExecutor.setMaxPoolSize(20);		// POOL MAX SIZE
		taskExecutor.setQueueCapacity(120);	// QUEUE MAX SIZE
		taskExecutor.setThreadNamePrefix("Executor-");
		taskExecutor.initialize();
		return taskExecutor;
	}
}