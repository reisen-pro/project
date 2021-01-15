package com.project.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    /**
     * 线程池数量
     */
    private static final int POOL_SIZE = 10;

    /**
     * 配置任务
     * @param scheduledTaskRegistrar 计划任务注册器
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // 设置线程池大小
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        // 设置线程前缀名
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        // 初始化
        threadPoolTaskScheduler.initialize();
        // 放到注册器中
        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}
