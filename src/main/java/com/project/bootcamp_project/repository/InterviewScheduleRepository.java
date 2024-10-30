package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.InterviewSchedule;
import com.project.bootcamp_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, String> {

    Page<InterviewSchedule> findAllByUser(User user, Pageable pageable);

}
