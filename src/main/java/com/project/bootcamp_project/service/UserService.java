package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.UserRepository;
import com.project.bootcamp_project.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IService<User> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserService() {
        this.jwtUtil = new JwtUtil();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return DefaultResponse.alreadyExists("FV001001001", request);
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved("FE001001001", request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, User user, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> login(String email, String password, HttpServletRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return DefaultResponse.notFound("FV001001002", request);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return DefaultResponse.invalidCredential("FV001001003", request);
        }

        try {
            String token = jwtUtil.generateToken(user.getEmail());
            return DefaultResponse.successWithData(token, request);
        } catch (Exception e) {
            return DefaultResponse.failed("FE001001002", request);
        }
    }


}
