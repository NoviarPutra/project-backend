package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.UserInterviewScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/interview-schedule")
public class UserInterviewScheduleController {

    @Autowired
    private UserInterviewScheduleService userInterviewScheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInterviewScheduleById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return userInterviewScheduleService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchInterviewSchedules(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userInterviewScheduleService.search(request, pageable);
    }

}

