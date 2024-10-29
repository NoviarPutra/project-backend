package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.SelectionProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionProcessRepository extends JpaRepository<SelectionProcess, String> {
}
