package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, String> {

}
