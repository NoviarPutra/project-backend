package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.InterviewScheduleMapper;
import com.project.bootcamp_project.dto.request.CreateInterviewScheduleDTO;
import com.project.bootcamp_project.entity.InterviewSchedule;
import com.project.bootcamp_project.service.InterviewScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interview-schedule")
public class InterviewScheduleController {

    @Autowired
    private InterviewScheduleService interviewScheduleService;

    @Autowired
    private InterviewScheduleMapper interviewScheduleMapper;

    @PostMapping
    public ResponseEntity<Object> addInterviewSchedule(@Valid @RequestBody CreateInterviewScheduleDTO createInterviewScheduleDTO, HttpServletRequest request) {
        InterviewSchedule interviewSchedule = interviewScheduleMapper.convertToEntity(createInterviewScheduleDTO);
        return interviewScheduleService.save(interviewSchedule, request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllInterviewSchedules(HttpServletRequest request) {
        return interviewScheduleService.findAll(request);
    }

}

