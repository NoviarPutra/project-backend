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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminInterviewScheduleService implements IService<InterviewSchedule> {

    @Autowired
    private InterviewScheduleRepository interviewScheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(InterviewSchedule interviewSchedule, HttpServletRequest request) {
        try {
            interviewScheduleRepository.save(interviewSchedule);
            Console.Log("InterviewSchedule saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, InterviewSchedule interviewSchedule, HttpServletRequest request) {
        Optional<InterviewSchedule> existingInterviewSchedule = interviewScheduleRepository.findById(id.toString());
        if (existingInterviewSchedule.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(interviewSchedule, existingInterviewSchedule.get());
        try {
            interviewScheduleRepository.save(existingInterviewSchedule.get());
            Console.Log("InterviewSchedule saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<InterviewSchedule> existingInterviewSchedule = interviewScheduleRepository.findById(id.toString());
        if (existingInterviewSchedule.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            interviewScheduleRepository.delete(existingInterviewSchedule.get());
            Console.Log("InterviewSchedule deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<InterviewSchedule> existingInterviewSchedule = interviewScheduleRepository.findById(id.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();
        if (existingInterviewSchedule.isPresent()) {
            if (existingInterviewSchedule.get().getUser().getEmail().equals(userEmail)) {
                InterviewScheduleResponseDTO interviewScheduleResponse = modelMapper.map(existingInterviewSchedule.get(), InterviewScheduleResponseDTO.class);
                return DefaultResponse.found(interviewScheduleResponse, request);
            }
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<InterviewSchedule> interviewSchedulesPaginated = interviewScheduleRepository.findAll(pageable);
        List<InterviewScheduleResponseDTO> interviewSchedulesResponse = interviewSchedulesPaginated.getContent().stream()
                .map(interviewSchedule -> modelMapper.map(interviewSchedule, InterviewScheduleResponseDTO.class))
                .toList();
        int totalPages = interviewSchedulesPaginated.getTotalPages();
        long totalItems = interviewSchedulesPaginated.getTotalElements();
        int currentPage = interviewSchedulesPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                interviewSchedulesResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}
