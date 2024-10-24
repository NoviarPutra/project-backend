package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findByName(String name);
    Optional<Department> findById(String id);
    List<Department> findAll();
}
