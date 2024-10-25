package com.project.bootcamp_project.service;

import com.project.bootcamp_project.core.IService;
import com.project.bootcamp_project.entity.Department;
import com.project.bootcamp_project.handler.DefaultResponse;
import com.project.bootcamp_project.repository.DepartmentRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService implements IService<Department> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<Object> save(Department department, HttpServletRequest request) {
        Optional<Department> existingDepartement = departmentRepository.findByName(department.getName());
        if(existingDepartement.isPresent()) {
            return DefaultResponse.alreadyExists(request);
        }
        try{
            departmentRepository.save(department);
            Console.Log("Department saved successfully");
            return DefaultResponse.saved(request);
        }catch (Exception e) {
            return DefaultResponse.failedSaved(request);
        }

    }

    @Override
    public ResponseEntity<Object> update(UUID id, Department department, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(UUID id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(UUID id, HttpServletRequest request) {
        Optional<Department> department = departmentRepository.findById(String.valueOf(id));

        if(department.isPresent()) {
            Console.Log("Department found with id: " + id);
            return DefaultResponse.found(department, request);
        }else {
            Console.Log("Department not found with id: " + id);
            return DefaultResponse.notFound(request);
        }
    }

    @Override
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        List<Department> departments = departmentRepository.findAll();

        if(!departments.isEmpty()) {
            Console.Log("Department found");
            return DefaultResponse.found(departments, request);
        }else {
            Console.Log("Department not found");
            return DefaultResponse.notFound(request);
        }
    }

}
