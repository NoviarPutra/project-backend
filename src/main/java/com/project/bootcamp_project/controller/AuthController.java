package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.request.LoginDTO;
import com.project.bootcamp_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        return userService.login(email, password, request);
    }

    @GetMapping("refresh_token")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
        return userService.refreshToken(request);
    }
}
