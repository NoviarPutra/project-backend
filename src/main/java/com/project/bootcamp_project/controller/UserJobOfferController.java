package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.UserJobOfferService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/job-offer")
public class UserJobOfferController {

    @Autowired
    private UserJobOfferService userJobOfferService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getJobOfferById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return userJobOfferService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchJobOffers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userJobOfferService.search(request, pageable);
    }

}
