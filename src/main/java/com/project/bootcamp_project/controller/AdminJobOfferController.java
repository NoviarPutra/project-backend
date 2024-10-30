package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.JobOfferMapper;
import com.project.bootcamp_project.dto.request.AdminCreateJobOfferDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateJobOfferDTO;
import com.project.bootcamp_project.entity.JobOffer;
import com.project.bootcamp_project.service.AdminJobOfferService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/job-offer")
public class AdminJobOfferController {

    @Autowired
    private AdminJobOfferService adminJobOfferService;

    @Autowired
    private JobOfferMapper jobOfferMapper;

    @PostMapping
    public ResponseEntity<Object> addJobOffer(
            @Valid @RequestBody AdminCreateJobOfferDTO adminCreateJobOfferDTO,
            HttpServletRequest request
    ) {
        JobOffer jobOffer = jobOfferMapper.convertToEntity(adminCreateJobOfferDTO);
        return adminJobOfferService.save(jobOffer, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobOffer(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateJobOfferDTO adminUpdateJobOfferDTO,
            HttpServletRequest request
    ) {
        JobOffer jobOffer = jobOfferMapper.convertToEntity(adminUpdateJobOfferDTO);
        return adminJobOfferService.update(id, jobOffer, request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobOffer(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminJobOfferService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobOfferById(@PathVariable UUID id, HttpServletRequest request) {
        return adminJobOfferService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchJobOffers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminJobOfferService.search(request, pageable);
    }

}
