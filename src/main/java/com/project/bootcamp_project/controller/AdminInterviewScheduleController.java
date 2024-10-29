package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.InterviewScheduleMapper;
import com.project.bootcamp_project.dto.request.AdminCreateInterviewScheduleDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateInterviewScheduleDTO;
import com.project.bootcamp_project.entity.InterviewSchedule;
import com.project.bootcamp_project.service.AdminInterviewScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/interview-schedule")
public class AdminInterviewScheduleController {

    @Autowired
    private AdminInterviewScheduleService adminInterviewScheduleService;

    @Autowired
    private InterviewScheduleMapper interviewScheduleMapper;

    @PostMapping
    public ResponseEntity<Object> addInterviewSchedule(
            @Valid @RequestBody AdminCreateInterviewScheduleDTO adminCreateInterviewScheduleDTO,
            HttpServletRequest request
    ) {
        InterviewSchedule interviewSchedule = interviewScheduleMapper.convertToEntity(adminCreateInterviewScheduleDTO);
        return adminInterviewScheduleService.save(interviewSchedule, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInterviewSchedule(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateInterviewScheduleDTO adminUpdateInterviewScheduleDTO,
            HttpServletRequest request
    ) {
        InterviewSchedule interviewSchedule = interviewScheduleMapper.convertToEntity(adminUpdateInterviewScheduleDTO);
        return adminInterviewScheduleService.update(id, interviewSchedule, request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInterviewSchedule(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminInterviewScheduleService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInterviewScheduleById(@PathVariable UUID id, HttpServletRequest request) {
        return adminInterviewScheduleService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchInterviewSchedules(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminInterviewScheduleService.search(request, pageable);
    }

}
