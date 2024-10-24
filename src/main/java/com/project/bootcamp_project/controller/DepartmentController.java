package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.request.CreateDepartmentDTO;
import com.project.bootcamp_project.entity.Department;
import com.project.bootcamp_project.service.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
private DepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("add")
    public ResponseEntity<Object> addDepartment(@Valid @RequestBody CreateDepartmentDTO createDepartmentDTO, HttpServletRequest request) {
        Department department = modelMapper.map(createDepartmentDTO, Department.class);
        return departmentService.save(department,request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllDepartments(HttpServletRequest request) {
        return departmentService.findAll(request);
    }

}
