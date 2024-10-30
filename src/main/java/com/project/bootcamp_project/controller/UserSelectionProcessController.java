package com.project.bootcamp_project.controller;

import com.project.bootcamp_project.service.UserSelectionProcessService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/selection-process")
public class UserSelectionProcessController {

    @Autowired
    private UserSelectionProcessService userSelectionProcessService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSelectionProcessById(
            @PathVariable UUID id,
            HttpServletRequest request
    ) {
        return userSelectionProcessService.findById(id, request);
    }

    @GetMapping
    public ResponseEntity<Object> searchSelectionProcesses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userSelectionProcessService.search(request, pageable);
    }

}
