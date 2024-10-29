package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.SelectionProcessMapper;
import com.project.bootcamp_project.dto.request.CreateSelectionProcessDTO;
import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.service.SelectionProcessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selection-process")
public class SelectionProcessController {

    @Autowired
    private SelectionProcessService selectionProcessService;

    @Autowired
    private SelectionProcessMapper selectionProcessMapper;

    @PostMapping
    public ResponseEntity<Object> addSelectionProcess(@Valid @RequestBody CreateSelectionProcessDTO createSelectionProcessDTO, HttpServletRequest request) {
        SelectionProcess selectionProcess = selectionProcessMapper.convertToEntity(createSelectionProcessDTO);
        return selectionProcessService.save(selectionProcess, request);
    }

    @GetMapping
    public ResponseEntity<Object> getAllSelectionProcesses(HttpServletRequest request) {
        return selectionProcessService.findAll(request);
    }

}
