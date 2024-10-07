package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.request.RegisterDTO;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RegisterDTO registerDTO, HttpServletRequest request) {
        User user = modelMapper.map(registerDTO, User.class);
        return userService.save(user, request);
    }
}
