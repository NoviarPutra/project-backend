package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.CandidateMapper;
import com.project.bootcamp_project.dto.request.CreateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.service.CandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @PostMapping
    public ResponseEntity<Object> addCandidate(
            @Valid @RequestBody CreateCandidateDTO createCandidateDTO,
            HttpServletRequest request
    ) {
        Candidate candidate = candidateMapper.convertToEntity(createCandidateDTO);
        return candidateService.save(candidate, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCandidateById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return candidateService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchCandidates(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return candidateService.search(request, pageable);
    }

}
