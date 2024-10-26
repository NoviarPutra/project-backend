package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.InterviewScheduleResponseDTO;
import com.project.bootcamp_project.entity.InterviewSchedule;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.InterviewScheduleRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterviewScheduleService implements IService<InterviewSchedule> {

    @Autowired
    private InterviewScheduleRepository interviewScheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(InterviewSchedule interviewSchedule, HttpServletRequest request) {
        try{
            interviewScheduleRepository.save(interviewSchedule);
            Console.Log("InterviewSchedule saved successfully");
            return DefaultResponse.saved(request);
        }catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, InterviewSchedule interviewSchedule, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<InterviewSchedule> interviewSchedules = interviewScheduleRepository.findAll();
        if (!interviewSchedules.isEmpty()) {
            List<InterviewScheduleResponseDTO> interviewScheduleResponse = interviewSchedules.stream()
                    .map(interviewSchedule -> modelMapper.map(interviewSchedule, InterviewScheduleResponseDTO.class))
                    .collect(Collectors.toList());
            Console.Log("InterviewSchedule found");
            return DefaultResponse.found(interviewScheduleResponse, request);
        } else {
            Console.Log("InterviewSchedule not found");
            return DefaultResponse.notFound(request);
        }
    }

}