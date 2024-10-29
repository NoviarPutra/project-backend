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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SelectionProcessService implements IService<SelectionProcess> {

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
        List<SelectionProcess> selectionProcesses = selectionProcessRepository.findAll();
        if (!selectionProcesses.isEmpty()) {
            List<SelectionProcessResponseDTO> selectionProcessResponse = selectionProcesses.stream()
                    .map(selectionProcess -> modelMapper.map(selectionProcess, SelectionProcessResponseDTO.class))
                    .collect(Collectors.toList());
            Console.Log("SelectionProcess found");
            return DefaultResponse.found(selectionProcessResponse, request);
        } else {
            Console.Log("SelectionProcess not found");
            return DefaultResponse.notFound(request);
        }
    }

}
