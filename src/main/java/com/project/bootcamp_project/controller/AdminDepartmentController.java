package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.request.AdminCreateDepartmentDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateDepartmentDTO;
import com.project.bootcamp_project.entity.Department;
import com.project.bootcamp_project.service.AdminDepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/department")
public class AdminDepartmentController {

    @Autowired
    private AdminDepartmentService adminDepartmentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Object> addDepartment(
            @Valid @RequestBody AdminCreateDepartmentDTO adminCreateDepartmentDTO,
            HttpServletRequest request
    ) {
        Department department = modelMapper.map(adminCreateDepartmentDTO, Department.class);
        return adminDepartmentService.save(department, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDepartment(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateDepartmentDTO adminUpdateDepartmentDTO,
            HttpServletRequest request
    ) {
        Department department = modelMapper.map(adminUpdateDepartmentDTO, Department.class);
        return adminDepartmentService.update(id, department, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartment(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminDepartmentService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable UUID id, HttpServletRequest request) {
        return adminDepartmentService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchDepartments(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminDepartmentService.search(request, pageable);
    }

}