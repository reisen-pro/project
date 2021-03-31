package com.project.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置类
 * Spring 默认使用的是 ThreadPoolExecutor.AbortPolicy。
 * 在Spring的默认情况下，ThreadPoolExecutor 将抛出 RejectedExecutionException 来拒绝新来的任务 ，这代表你将丢失对这个任务的处理。
 *
 * ThreadPoolExecutor.AbortPolicy：
 * 抛出 RejectedExecutionException来拒绝新任务的处理。
 *
 * ThreadPoolExecutor.CallerRunsPolicy：
 * 调用执行自己的线程运行任务。您不会任务请求。
 * 但是这种策略会降低对于新任务提交速度，影响程序的整体性能。另外，这个策略喜欢增加队列容量。
 * 如果您的应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，你可以选择这个策略。
 *
 * ThreadPoolExecutor.DiscardPolicy：
 * 不处理新任务，直接丢弃掉。
 *
 * ThreadPoolExecutor.DiscardOldestPolicy：
 * 此策略将丢弃最早的未处理的任务请求。
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    // 核心线程数量
    private static final int CORE_POOL_SIZE = 3;
    // 最大线程数量
    private static final int MAX_POOL_SIZE = 10;
    // 队列容量
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 创建一个任务执行者
     * @return Executor
     */
    @Bean
    public Executor taskExecutor() {
        // Spring 默认配置是核心线程数大小为1，最大线程容量大小不受限制，队列容量也不受限制。
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        // 最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 队列大小
        executor.setQueueCapacity(QUEUE_CAPACITY);
        // 当最大池已满时，此策略保证不会丢失任务请求，但是可能会影响应用程序整体性能。
        // 设置拒绝执行程序策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程前缀名
        executor.setThreadNamePrefix("My ThreadPoolTaskExecutor-");
        // 初始化
        executor.initialize();
        return executor;
    }
}