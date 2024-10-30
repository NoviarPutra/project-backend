package com.project.bootcamp_project.config;

import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.UserRepository;
import com.project.bootcamp_project.service.AuditAwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class AuditConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditAwareService(userRepository);
    }

}

