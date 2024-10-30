package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionProcessRepository extends JpaRepository<SelectionProcess, String> {

    Page<SelectionProcess> findAllByCandidate_User(User user, Pageable pageable);

}
