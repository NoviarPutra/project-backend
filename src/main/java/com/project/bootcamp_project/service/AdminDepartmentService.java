package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.dto.response.DepartmentResponseDTO;
import com.project.bootcamp_project.entity.Department;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.DepartmentRepository;
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
public class AdminDepartmentService implements IService<Department> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> save(Department department, HttpServletRequest request) {
        try {
            departmentRepository.save(department);
            Console.Log("Department saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> update(UUID id, Department department, HttpServletRequest request) {
        Optional<Department> existingDepartment = departmentRepository.findById(id.toString());
        if (existingDepartment.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(department, existingDepartment.get());
        try {
            departmentRepository.save(existingDepartment.get());
            Console.Log("Department saved successfully");
            return DefaultResponse.saved(request);
        } catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        Optional<Department> existingDepartment = departmentRepository.findById(id.toString());
        if (existingDepartment.isEmpty()) {
            return DefaultResponse.notFound(request);
        }
        try {
            departmentRepository.delete(existingDepartment.get());
            Console.Log("Department deleted successfully");
            return DefaultResponse.deleted(request);
        } catch (Exception e) {
            return DefaultResponse.failedDeleted(request);
        }
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<Department> existingDepartment = departmentRepository.findById(id.toString());
        if (existingDepartment.isPresent()) {
            DepartmentResponseDTO departmentResponse = modelMapper.map(existingDepartment.get(), DepartmentResponseDTO.class);
            return DefaultResponse.found(departmentResponse, request);
        }
        return DefaultResponse.notFound(request);
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        return null;
    }

    public ResponseEntity<Object> search(HttpServletRequest request, Pageable pageable) {
        Page<Department> departmentsPaginated = departmentRepository.findAll(pageable);
        List<DepartmentResponseDTO> departmentsResponse = departmentsPaginated.getContent().stream()
                .map(department -> modelMapper.map(department, DepartmentResponseDTO.class))
                .toList();
        int totalPages = departmentsPaginated.getTotalPages();
        long totalItems = departmentsPaginated.getTotalElements();
        int currentPage = departmentsPaginated.getNumber();
        return DefaultResponse.foundWithPagination(
                departmentsResponse,
                request,
                currentPage,
                (int) totalItems,
                totalPages
        );
    }

}