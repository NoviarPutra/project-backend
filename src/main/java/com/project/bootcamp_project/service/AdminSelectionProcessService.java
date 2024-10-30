package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.SelectionProcessResponseDTO;
import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.SelectionProcessRepository;
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
public class AdminSelectionProcessService implements IService<SelectionProcess> {

    @Autowired
    private SelectionProcessRepository selectionProcessRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(SelectionProcess selectionProcess, HttpServletRequest request) {
        try {
            selectionProcessRepository.save(selectionProcess);
            Console.Log("SelectionProcess saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, SelectionProcess selectionProcess, HttpServletRequest request) {
        Optional<SelectionProcess> existingSelectionProcess = selectionProcessRepository.findById(id.toString());
        if (existingSelectionProcess.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(selectionProcess, existingSelectionProcess.get());
        try {
            selectionProcessRepository.save(existingSelectionProcess.get());
            Console.Log("SelectionProcess saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<SelectionProcess> existingSelectionProcess = selectionProcessRepository.findById(id.toString());
        if (existingSelectionProcess.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            selectionProcessRepository.delete(existingSelectionProcess.get());
            Console.Log("SelectionProcess deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<SelectionProcess> existingSelectionProcess = selectionProcessRepository.findById(id.toString());
        if (existingSelectionProcess.isPresent()) {
            SelectionProcessResponseDTO selectionProcessResponse = modelMapper.map(existingSelectionProcess.get(), SelectionProcessResponseDTO.class);
            return DefaultResponse.found(selectionProcessResponse, request);
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<SelectionProcess> selectionProcesssPaginated = selectionProcessRepository.findAll(pageable);
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
