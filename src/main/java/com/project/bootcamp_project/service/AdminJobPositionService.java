package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.JobPositionResponseDTO;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.JobPositionRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminJobPositionService implements IService<JobPosition> {

    @Autowired
    private JobPositionRepository jobPositionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(JobPosition jobPosition, HttpServletRequest request) {
        try {
            jobPositionRepository.save(jobPosition);
            Console.Log("JobPosition saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, JobPosition jobPosition, HttpServletRequest request) {
        Optional<JobPosition> existingJobPosition = jobPositionRepository.findById(id.toString());
        if (existingJobPosition.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(jobPosition, existingJobPosition.get());
        try {
            jobPositionRepository.save(existingJobPosition.get());
            Console.Log("JobPosition saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            Console.Log(e.getMessage());
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<JobPosition> existingJobPosition = jobPositionRepository.findById(id.toString());
        if (existingJobPosition.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            jobPositionRepository.delete(existingJobPosition.get());
            Console.Log("JobPosition deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<JobPosition> existingJobPosition = jobPositionRepository.findById(id.toString());
        if (existingJobPosition.isPresent()) {
            JobPositionResponseDTO jobPositionResponse = modelMapper.map(existingJobPosition.get(), JobPositionResponseDTO.class);
            return DefaultResponse.found(jobPositionResponse, request);
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<JobPosition> jobPositionsPaginated = jobPositionRepository.findAll(pageable);
        List<JobPositionResponseDTO> jobPositionsResponse = jobPositionsPaginated.getContent().stream()
                .map(jobPosition -> modelMapper.map(jobPosition, JobPositionResponseDTO.class))
                .toList();
        int totalPages = jobPositionsPaginated.getTotalPages();
        long totalItems = jobPositionsPaginated.getTotalElements();
        int currentPage = jobPositionsPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                jobPositionsResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}
