package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.InterviewScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/interview-schedule")
public class InterviewScheduleController {

    @Autowired
    private InterviewScheduleService interviewScheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInterviewScheduleById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return interviewScheduleService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchInterviewSchedule(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return interviewScheduleService.search(request, pageable);
    }

}

