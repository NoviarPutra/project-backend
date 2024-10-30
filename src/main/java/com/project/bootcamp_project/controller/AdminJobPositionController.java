package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.JobPositionMapper;
import com.project.bootcamp_project.dto.request.AdminCreateJobPositionDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateJobPositionDTO;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.service.AdminJobPositionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/job-position")
public class AdminJobPositionController {

    @Autowired
    private AdminJobPositionService adminJobPositionService;

    @Autowired
    private JobPositionMapper jobPositionMapper;

    @PostMapping
    public ResponseEntity<Object> addJobPosition(
            @Valid @RequestBody AdminCreateJobPositionDTO adminCreateJobPositionDTO,
            HttpServletRequest request
    ) {
        JobPosition jobPosition = jobPositionMapper.convertToEntity(adminCreateJobPositionDTO);
        return adminJobPositionService.save(jobPosition, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobPosition(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateJobPositionDTO adminUpdateJobPositionDTO,
            HttpServletRequest request
    ) {
        JobPosition jobPosition = jobPositionMapper.convertToEntity(adminUpdateJobPositionDTO);
        return adminJobPositionService.update(id, jobPosition, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobPosition(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminJobPositionService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobPositionById(@PathVariable UUID id, HttpServletRequest request) {
        return adminJobPositionService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchJobPositions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminJobPositionService.search(request, pageable);
    }

}
