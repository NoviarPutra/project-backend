package com.project.bootcamp_project.dto.mapper;

import com.project.bootcamp_project.dto.CandidateStatus;
import com.project.bootcamp_project.dto.request.CreateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.JobPositionRespository;
import com.project.bootcamp_project.repository.UserRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    @Autowired
    private JobPositionRespository jobPositionRepository;

    @Autowired
    private UserRepository userRepository;

    public Candidate convertToEntity(CreateCandidateDTO createCandidateDTO) {
        return buildCandidate(createCandidateDTO.getJobPositionId(), createCandidateDTO.getResume());
    }

    private Candidate buildCandidate(String jobPositionId, String resume) {
        Candidate candidate = new Candidate();

        candidate.setUser(getAuthenticatedUser());
        candidate.setJobPosition(getJobPositionById(jobPositionId));
        candidate.setStatus(CandidateStatus.APPLIED);
        candidate.setResume(resume);

        return candidate;
    }

    private User getAuthenticatedUser() {
        return userRepository.findAll().get(0);

//        Perlu gunakan code dibawah apabila authentikasi jwtnya sudah berjalan

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return userRepository.findByEmail((String) authentication.getPrincipal()).orElseThrow(() -> {
//            Console.Log("User not found");
//            return new EntityNotFoundException("User not found for email: " + authentication.getPrincipal());
//        });

    }

    private JobPosition getJobPositionById(String jobPositionId) {
        return jobPositionRepository.findById(jobPositionId).orElseThrow(() -> {
            Console.Log("Job position not found");
            return new EntityNotFoundException("Job position not found for ID: " + jobPositionId);
        });
    }

}
