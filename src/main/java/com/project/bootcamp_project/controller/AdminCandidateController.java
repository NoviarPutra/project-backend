package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.CandidateMapper;
import com.project.bootcamp_project.dto.request.AdminUpdateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.service.AdminCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/candidate")
public class AdminCandidateController {

    @Autowired
    private AdminCandidateService adminCandidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCandidate(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateCandidateDTO adminUpdateCandidateDTO,
            HttpServletRequest request
    ) {
        Candidate candidate = candidateMapper.convertToEntity(adminUpdateCandidateDTO);
        return adminCandidateService.update(id, candidate, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCandidate(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminCandidateService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCandidateById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminCandidateService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchCandidates(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminCandidateService.search(request, pageable);
    }

}
