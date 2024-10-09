package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {
    Optional<Role> findByName(String name);
}
