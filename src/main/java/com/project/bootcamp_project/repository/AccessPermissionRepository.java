package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.AccessPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessPermissionRepository extends JpaRepository<AccessPermission, String> {
    Optional<AccessPermission> findByName(String name);
}
