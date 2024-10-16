package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.UserResponseDTO;
import com.project.bootcamp_project.entity.AccessPermission;
import com.project.bootcamp_project.entity.Role;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.RoleRepository;
import com.project.bootcamp_project.repository.UserRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IService<User> {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserService() {
        this.jwtService = new JwtService();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        Console.Info(user.toString());
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            Console.Error("User already exists");
            return DefaultResponse.alreadyExists(request);
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getRole() == null) {
                Optional<Role> defaultRole = roleRepository.findByName("CANDIDATE");
                if (defaultRole.isPresent()) {
                    user.setRole(defaultRole.get());
                } else {
                    Role candidate = new Role();
                    candidate.setName("CANDIDATE");
                    user.setRole(roleRepository.save(candidate));
                }
            }
            userRepository.save(user);
            Console.Log("User saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            Console.Error("Failed to save user");
            return DefaultResponse.failedSaved(request);
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

    public ResponseEntity<Object> findUserByEmail(String email, HttpServletRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            Console.Error("User not found");
            return DefaultResponse.notFound(request);
        }
        User user = userOptional.get();
        Set<String> permissions = user.getRole().getAccessPermissions().stream()
                .map(AccessPermission::getName)
                .collect(Collectors.toSet());
        UserResponseDTO userResponse = new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getRole().getName(),
                permissions
        );

        Console.Log("Email " + email + " found successfully");
        return DefaultResponse.successWithData(userResponse, request);
    }

    public ResponseEntity<Object> login(String email, String password, HttpServletRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            Console.Error("User not found");
            return DefaultResponse.notFound(request);
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            Console.Error("Wrong password");
            return DefaultResponse.invalidCredential(request);
        }

        try {
            String token = jwtService.generateToken(user.getEmail());
            Console.Log("Successfully logged in");
            return DefaultResponse.successWithData(token, request);
        } catch (Exception e) {
            Console.Error("Failed to login");
            return DefaultResponse.failed(request);
        }
    }

}
