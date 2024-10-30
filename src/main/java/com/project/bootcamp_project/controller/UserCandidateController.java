package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.CandidateMapper;
import com.project.bootcamp_project.dto.request.UserCreateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.service.UserCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/candidate")
public class UserCandidateController {

    @Autowired
    private UserCandidateService userCandidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @PostMapping
    public ResponseEntity<Object> addCandidate(
            @Valid @RequestBody UserCreateCandidateDTO userCreateCandidateDTO,
            HttpServletRequest request
    ) {
        Candidate candidate = candidateMapper.convertToEntity(userCreateCandidateDTO);
        return userCandidateService.save(candidate, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCandidateById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return userCandidateService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchCandidates(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userCandidateService.search(request, pageable);
    }

}
