package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.dto.mapper.SelectionProcessMapper;
import com.project.bootcamp_project.dto.request.AdminCreateSelectionProcessDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateSelectionProcessDTO;
import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.service.AdminSelectionProcessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/selection-process")
public class AdminSelectionProcessController {

    @Autowired
    private AdminSelectionProcessService adminSelectionProcessService;

    @Autowired
    private SelectionProcessMapper selectionProcessMapper;

    @PostMapping
    public ResponseEntity<Object> addSelectionProcess(
            @Valid @RequestBody AdminCreateSelectionProcessDTO adminCreateSelectionProcessDTO,
            HttpServletRequest request
    ) {
        SelectionProcess selectionProcess = selectionProcessMapper.convertToEntity(adminCreateSelectionProcessDTO);
        return adminSelectionProcessService.save(selectionProcess, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSelectionProcess(
            @PathVariable UUID id,
            @Valid @RequestBody AdminUpdateSelectionProcessDTO adminUpdateSelectionProcessDTO,
            HttpServletRequest request
    ) {
        SelectionProcess selectionProcess = selectionProcessMapper.convertToEntity(adminUpdateSelectionProcessDTO);
        return adminSelectionProcessService.update(id, selectionProcess, request);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSelectionProcess(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return adminSelectionProcessService.delete(id, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSelectionProcessById(@PathVariable UUID id, HttpServletRequest request) {
        return adminSelectionProcessService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchSelectionProcesses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminSelectionProcessService.search(request, pageable);
    }

}
