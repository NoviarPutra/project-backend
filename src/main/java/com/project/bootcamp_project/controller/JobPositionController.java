package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.JobPositionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/job-position")
public class JobPositionController {

    @Autowired
    private JobPositionService jobPositionService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobPositionById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return jobPositionService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchJobPositions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return jobPositionService.search(request, pageable);
    }

}
