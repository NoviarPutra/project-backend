package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition, String> {
}
