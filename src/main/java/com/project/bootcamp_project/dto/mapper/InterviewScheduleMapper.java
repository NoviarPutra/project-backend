package com.project.bootcamp_project.dto.mapper;

import com.project.bootcamp_project.dto.request.AdminCreateInterviewScheduleDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateInterviewScheduleDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.InterviewSchedule;
import com.project.bootcamp_project.entity.User;
import com.project.bootcamp_project.repository.CandidateRepository;
import com.project.bootcamp_project.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterviewScheduleMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserRepository userRepository;

    private Converter<String, Candidate> candidateConverter;

    private Converter<String, User> interviewerConverter;

    @PostConstruct
    public void init() {
        candidateConverter = new Converter<String, Candidate>() {
            public Candidate convert(MappingContext<String, Candidate> context) {
                return candidateRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Invalid Candidate ID: " + context.getSource()));
            }
        };
        interviewerConverter = new Converter<String, User>() {
            public User convert(MappingContext<String, User> context) {
                return userRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Invalid Interviewer ID: " + context.getSource()));
            }
        };
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public InterviewSchedule convertToEntity(AdminCreateInterviewScheduleDTO adminCreateInterviewScheduleDTO) {
        modelMapper.typeMap(AdminCreateInterviewScheduleDTO.class, InterviewSchedule.class).addMappings(mapper -> {
            mapper.using(candidateConverter).map(AdminCreateInterviewScheduleDTO::getCandidateId, InterviewSchedule::setCandidate);
            mapper.using(interviewerConverter).map(AdminCreateInterviewScheduleDTO::getInterviewerId, InterviewSchedule::setUser);
            mapper.map(AdminCreateInterviewScheduleDTO::getScheduleTime, InterviewSchedule::setScheduleTime);
            mapper.skip(InterviewSchedule::setId);
        });
        return modelMapper.map(adminCreateInterviewScheduleDTO, InterviewSchedule.class);
    }

    public InterviewSchedule convertToEntity(AdminUpdateInterviewScheduleDTO adminUpdateInterviewScheduleDTO) {
        modelMapper.typeMap(AdminUpdateInterviewScheduleDTO.class, InterviewSchedule.class).addMappings(mapper -> {
            mapper.using(candidateConverter).map(AdminUpdateInterviewScheduleDTO::getCandidateId, InterviewSchedule::setCandidate);
            mapper.using(interviewerConverter).map(AdminUpdateInterviewScheduleDTO::getInterviewerId, InterviewSchedule::setUser);
            mapper.map(AdminUpdateInterviewScheduleDTO::getScheduleTime, InterviewSchedule::setScheduleTime);
            mapper.skip(InterviewSchedule::setId);
        });
        return modelMapper.map(adminUpdateInterviewScheduleDTO, InterviewSchedule.class);
    }

}

