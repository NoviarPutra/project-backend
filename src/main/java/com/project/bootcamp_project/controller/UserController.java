package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.UserService;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<Object> findByEmail(
            @NotBlank(message = "email tidak boleh kosong")
            @Email(message = "email tidak sesuai")
            @RequestParam String email,
            HttpServletRequest request) {

        Console.Log(email);
        return userService.findUserByEmail(email, request);
    }

}
