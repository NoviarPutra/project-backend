package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.UserService;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @GetMapping("search")
    public ResponseEntity<Object> searchUsers(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "createdAtStart", required = false) String createdAtStart,
            @RequestParam(value = "createdAtEnd", required = false) String createdAtEnd,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request) {

        LocalDateTime startDate = createdAtStart != null ? LocalDateTime.parse(createdAtStart + "T00:00:00") : null;
        LocalDateTime endDate = createdAtEnd != null ? LocalDateTime.parse(createdAtEnd + "T23:59:59") : null;

        Pageable pageable = PageRequest.of(page, size);
        return userService.searchUser(email, roleName, id, startDate, endDate, request, pageable);
    }
}
