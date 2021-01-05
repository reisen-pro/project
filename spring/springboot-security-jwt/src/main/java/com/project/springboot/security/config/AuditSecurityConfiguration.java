package com.project.springboot.security.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @description 审计功能配置类
 */
@SpringBootConfiguration
@EnableJpaAuditing
public class AuditSecurityConfiguration {

    /**
     * AuditorAware 是一个只有一个方法需要实现的接口，符合函数式接口的要求，可以使用lambda表达式
     * @return
     */
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> {
            return Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getName);
        };
    }
}

