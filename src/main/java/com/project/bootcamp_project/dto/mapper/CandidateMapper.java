package com.project.bootcamp_project.dto.mapper;

import com.project.bootcamp_project.dto.CandidateStatus;
import com.project.bootcamp_project.dto.request.CreateCandidateDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.JobPositionRespository;
import com.project.bootcamp_project.repository.UserRepository;
import com.project.bootcamp_project.util.Console;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JobPositionRespository jobPositionRepository;

    @Autowired
    private UserRepository userRepository;

    private Converter<String, JobPosition> jobPositionConverter;
    private Converter<Void, User> userConverter;

    @PostConstruct
    public void init() {
        jobPositionConverter = new Converter<String, JobPosition>() {
            public JobPosition convert(MappingContext<String, JobPosition> context) {
                return jobPositionRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Job position not found for ID: " + context.getSource()));
            }
        };
        userConverter = new Converter<Void, User>() {
            public User convert(MappingContext<Void, User> context) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return userRepository.findByEmail((String) authentication.getPrincipal())
                        .orElseThrow(() -> new EntityNotFoundException("User not found for email: " + authentication.getPrincipal()));
            }
        };
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public Candidate convertToEntity(CreateCandidateDTO createCandidateDTO) {
        modelMapper.typeMap(CreateCandidateDTO.class, Candidate.class).addMappings(mapper -> {
            mapper.using(jobPositionConverter).map(CreateCandidateDTO::getJobPositionId, Candidate::setJobPosition);
            mapper.using(userConverter).map(source -> null, Candidate::setUser);
            mapper.map(CreateCandidateDTO::getResume, Candidate::setResume);
            mapper.map(src -> CandidateStatus.APPLIED, Candidate::setStatus);
        });
        return modelMapper.map(createCandidateDTO, Candidate.class);
    }

}