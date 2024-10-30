package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.JobOfferResponseDTO;
import com.project.bootcamp_project.entity.JobOffer;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.JobOfferRepository;
import com.project.bootcamp_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserJobOfferService implements IService<JobOffer> {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(JobOffer jobOffer, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(UUID id, JobOffer jobOffer, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<JobOffer> existingJobOffer = jobOfferRepository.findById(id.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();
        if (existingJobOffer.isPresent()) {
            if (existingJobOffer.get().getCandidate().getUser().getEmail().equals(userEmail)) {
                JobOfferResponseDTO jobOfferResponse = modelMapper.map(existingJobOffer.get(), JobOfferResponseDTO.class);
                return DefaultResponse.found(jobOfferResponse, request);
            }
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> existingUser = userRepository.findByEmail((String) authentication.getPrincipal());
        if (existingUser.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        Page<JobOffer> jobOffersPaginated = jobOfferRepository.findAllByCandidate_User(existingUser.get(), pageable);
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
