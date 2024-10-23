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
import com.project.bootcamp_project.util.EncryptionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
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
            String accessToken = jwtService.generateAccessToken(user.getEmail());
            String refreshToken = jwtService.generateRefreshToken(user.getEmail());
            String encryptedAccessToken = EncryptionUtil.encrypt(accessToken);
            String encryptedRefreshToken = EncryptionUtil.encrypt(refreshToken);


            Map<String, String> tokens = Map.of(
                    "access_token", encryptedAccessToken
//                    "refresh_token", encryptedRefreshToken
            );
//            Map<String, String> tokens = new HashMap<>();
            return DefaultResponse.successWithDataAndHeaders(
                    tokens,
                    request,
                    encryptedAccessToken,
                    encryptedRefreshToken
            );
        } catch (Exception e) {
            Console.Error("Failed to login : " + e.getMessage());
            return DefaultResponse.failed(request);
        }
    }

    public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
        try {
            String refreshToken = null;

            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if ("refresh_token".equals(cookie.getName())) {
                        refreshToken = cookie.getValue();
                        break;
                    }
                }
            }

            if (refreshToken == null) {
                Console.Error("Tidak ada refresh token");
                return DefaultResponse.invalidCredential(request);
            }

            String decryptedRefreshToken = EncryptionUtil.decrypt(refreshToken);


            String email = jwtService.extractEmail(decryptedRefreshToken);

            String newAccessToken = jwtService.generateAccessToken(email);
            String encryptedAccessToken = EncryptionUtil.encrypt(newAccessToken);

            return DefaultResponse.successWithDataAndHeaders(
//                    new HashMap<>(),
                    Map.of("access_token", encryptedAccessToken),
                    request,
                    encryptedAccessToken,
                    refreshToken
            );
        } catch (Exception e) {
            Console.Error("Failed to refresh token: " + e.getMessage());
            return DefaultResponse.invalidRefreshToken(request);
        }
    }

    public ResponseEntity<Object> logout(HttpServletRequest request) {
        try {
            return DefaultResponse.successWithDataAndHeadersForLogout(
                    new HashMap<>(),
                    request
            );

        } catch (Exception e) {
            Console.Error("Failed to logout: " + e.getMessage());
            return DefaultResponse.failed(request);
        }
    }

}
