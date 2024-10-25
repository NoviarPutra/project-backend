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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidateService implements IService<Candidate> {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(Candidate candidate, HttpServletRequest request) {
        Optional<Candidate> existingCandidate = candidateRepository.findByUserAndJobPosition(candidate.getUser(), candidate.getJobPosition());
        if (existingCandidate.isPresent()) {
            return DefaultResponse.alreadyExists(request);
        }
        try {
            candidateRepository.save(candidate);
            Console.Log("Candidate saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, Candidate candidate, HttpServletRequest request) {
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
        List<Candidate> candidates = candidateRepository.findAll();
        if (!candidates.isEmpty()) {
            List<CandidateResponseDTO> candidatesResponse = candidates.stream()
                    .map(candidate -> modelMapper.map(candidate, CandidateResponseDTO.class))
                    .collect(Collectors.toList());
            Console.Log("Candidate found");
            return DefaultResponse.found(candidatesResponse, request);
        } else {
            Console.Log("Candidate not found");
            return DefaultResponse.notFound(request);
        }
    }

}
