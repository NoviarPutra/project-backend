package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.request.RegisterDTO;
import com.project.bootcamp_project.entity.Role;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.exception.RoleNotFoundException;
import com.project.bootcamp_project.repository.RoleRepository;
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
    RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody RegisterDTO registerDTO, HttpServletRequest request) {
        if (registerDTO.getRole() == null) {
            User user = modelMapper.map(registerDTO, User.class);
            return userService.save(user, request);
        } else {
            Role role = roleRepository.findByName(registerDTO.getRole().toUpperCase())
                    .orElseThrow(() -> new RoleNotFoundException("Role " + registerDTO.getRole() + " not found"));
            User user = modelMapper.map(registerDTO, User.class);
            user.setRole(role);
            return userService.save(user, request);
        }
    }
}
