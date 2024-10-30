package com.project.bootcamp_project.repository;

import com.project.bootcamp_project.entity.JobOffer;
import com.project.bootcamp_project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<JobOffer, String> {

    Page<JobOffer> findAllByCandidate_User(User user, Pageable pageable);

}
