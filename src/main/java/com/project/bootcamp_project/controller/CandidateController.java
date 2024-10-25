package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.CandidateMapper;
import com.project.bootcamp_project.dto.request.CreateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.service.CandidateService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @PostMapping("/add")
    public ResponseEntity<Object> addCandidate(@Valid @RequestBody CreateCandidateDTO createCandidateDTO, HttpServletRequest request) {
        Candidate candidate;
        try {
            candidate = candidateMapper.convertToEntity(createCandidateDTO);
        } catch (EntityNotFoundException e) {
            return DefaultResponse.notFound(request);
        }
        return candidateService.save(candidate, request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCandidates(HttpServletRequest request) {
        return candidateService.findAll(request);
    }

}
