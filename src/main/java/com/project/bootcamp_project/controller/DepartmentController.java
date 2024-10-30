package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/public/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDepartmentById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return departmentService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchDepartments(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentService.search(request, pageable);
    }

}
