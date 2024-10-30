package com.project.bootcamp_project.dto.mapper;

import com.project.bootcamp_project.dto.OfferStatus;
import com.project.bootcamp_project.dto.request.AdminCreateJobOfferDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateJobOfferDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.JobOffer;
import com.project.bootcamp_project.repository.CandidateRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobOfferMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    private Converter<String, Candidate> candidateRepositoryConverter;

    private Converter<String, OfferStatus> offerStatusEnumConverter;

    @PostConstruct
    public void init() {
        candidateRepositoryConverter = new Converter<String, Candidate>() {
            public Candidate convert(MappingContext<String, Candidate> context) {
                return candidateRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Candidate not found for ID: " + context.getSource()));
            }
        };
        offerStatusEnumConverter = new Converter<String, OfferStatus>() {
            public OfferStatus convert(MappingContext<String, OfferStatus> context) {
                return OfferStatus.valueOf(context.getSource());
            }
        };
    }

    public JobOffer convertToEntity(AdminCreateJobOfferDTO createJobOfferDTO) {
        modelMapper.typeMap(AdminCreateJobOfferDTO.class, JobOffer.class).addMappings(mapper -> {
            mapper.using(candidateRepositoryConverter).map(AdminCreateJobOfferDTO::getCandidateId, JobOffer::setCandidate);
            mapper.using(offerStatusEnumConverter).map(AdminCreateJobOfferDTO::getStatus, JobOffer::setStatus);
            mapper.map(AdminCreateJobOfferDTO::getOfferDetails, JobOffer::setOfferDetails);
        });
        return modelMapper.map(createJobOfferDTO, JobOffer.class);
    }

    public JobOffer convertToEntity(AdminUpdateJobOfferDTO updateJobOfferDTO) {
        modelMapper.typeMap(AdminUpdateJobOfferDTO.class, JobOffer.class).addMappings(mapper -> {
            mapper.using(candidateRepositoryConverter).map(AdminUpdateJobOfferDTO::getCandidateId, JobOffer::setCandidate);
            mapper.using(offerStatusEnumConverter).map(AdminUpdateJobOfferDTO::getStatus, JobOffer::setStatus);
            mapper.map(AdminUpdateJobOfferDTO::getOfferDetails, JobOffer::setOfferDetails);
        });
        return modelMapper.map(updateJobOfferDTO, JobOffer.class);
    }

}
