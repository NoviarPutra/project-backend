package com.project.bootcamp_project.service;

import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAwareService implements AuditorAware<User> {

    private final UserRepository userRepository;

    public AuditAwareService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nonnull
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail((String) authentication.getPrincipal());
    }

}
