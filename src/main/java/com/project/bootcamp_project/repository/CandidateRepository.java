package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, String> {

    Page<Candidate> findAllByUser(User user, Pageable pageable);
    Optional<Candidate> findByUserAndJobPosition(User user, JobPosition jobPosition);

}

