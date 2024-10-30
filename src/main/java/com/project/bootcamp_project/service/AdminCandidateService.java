package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.CandidateResponseDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.CandidateRepository;
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
public class AdminCandidateService implements IService<Candidate> {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(Candidate candidate, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(UUID id, Candidate candidate, HttpServletRequest request) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id.toString());
        if (existingCandidate.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(candidate, existingCandidate.get());
        try {
            candidateRepository.save(existingCandidate.get());
            Console.Log("Candidate saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id.toString());
        if (existingCandidate.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            candidateRepository.delete(existingCandidate.get());
            Console.Log("Candidate deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(id.toString());
        if (existingCandidate.isPresent()) {
            CandidateResponseDTO candidateResponse = modelMapper.map(existingCandidate.get(), CandidateResponseDTO.class);
            return DefaultResponse.found(candidateResponse, request);
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<Candidate> candidatesPaginated = candidateRepository.findAll(pageable);
        List<CandidateResponseDTO> candidatesResponse = candidatesPaginated.getContent().stream()
                .map(candidate -> modelMapper.map(candidate, CandidateResponseDTO.class))
                .toList();
        int totalPages = candidatesPaginated.getTotalPages();
        long totalItems = candidatesPaginated.getTotalElements();
        int currentPage = candidatesPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                candidatesResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}
