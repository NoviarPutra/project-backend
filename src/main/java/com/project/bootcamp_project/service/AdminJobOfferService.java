package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.JobOfferResponseDTO;
import com.project.bootcamp_project.entity.JobOffer;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.JobOfferRepository;
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
public class AdminJobOfferService implements IService<JobOffer> {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(JobOffer jobOffer, HttpServletRequest request) {
        try {
            jobOfferRepository.save(jobOffer);
            Console.Log("JobOffer saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, JobOffer jobOffer, HttpServletRequest request) {
        Optional<JobOffer> existingJobOffer = jobOfferRepository.findById(id.toString());
        if (existingJobOffer.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(jobOffer, existingJobOffer.get());
        try {
            jobOfferRepository.save(existingJobOffer.get());
            Console.Log("JobOffer saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<JobOffer> existingJobOffer = jobOfferRepository.findById(id.toString());
        if (existingJobOffer.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            jobOfferRepository.delete(existingJobOffer.get());
            Console.Log("JobOffer deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<JobOffer> existingJobOffer = jobOfferRepository.findById(id.toString());
        if (existingJobOffer.isPresent()) {
            JobOfferResponseDTO jobOfferResponse = modelMapper.map(existingJobOffer.get(), JobOfferResponseDTO.class);
            return DefaultResponse.found(jobOfferResponse, request);
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<JobOffer> jobOffersPaginated = jobOfferRepository.findAll(pageable);
        List<JobOfferResponseDTO> jobOffersResponse = jobOffersPaginated.getContent().stream()
                .map(jobOffer -> modelMapper.map(jobOffer, JobOfferResponseDTO.class))
                .toList();
        int totalPages = jobOffersPaginated.getTotalPages();
        long totalItems = jobOffersPaginated.getTotalElements();
        int currentPage = jobOffersPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                jobOffersResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}
