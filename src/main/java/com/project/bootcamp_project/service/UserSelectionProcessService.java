package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.SelectionProcessResponseDTO;
import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.SelectionProcessRepository;
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
public class UserSelectionProcessService implements IService<SelectionProcess> {

    @Autowired
    private SelectionProcessRepository selectionProcessRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(SelectionProcess selectionProcess, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(UUID id, SelectionProcess selectionProcess, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<SelectionProcess> existingSelectionProcess = selectionProcessRepository.findById(id.toString());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();
        if (existingSelectionProcess.isPresent()) {
            if (existingSelectionProcess.get().getCandidate().getUser().getEmail().equals(userEmail)) {
                SelectionProcessResponseDTO selectionProcessResponse = modelMapper.map(existingSelectionProcess.get(), SelectionProcessResponseDTO.class);
                return DefaultResponse.found(selectionProcessResponse, request);
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
        Page<SelectionProcess> selectionProcesssPaginated = selectionProcessRepository.findAllByCandidate_User(existingUser.get(), pageable);
        List<SelectionProcessResponseDTO> selectionProcesssResponse = selectionProcesssPaginated.getContent().stream()
                .map(selectionProcess -> modelMapper.map(selectionProcess, SelectionProcessResponseDTO.class))
                .toList();
        int totalPages = selectionProcesssPaginated.getTotalPages();
        long totalItems = selectionProcesssPaginated.getTotalElements();
        int currentPage = selectionProcesssPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                selectionProcesssResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}
