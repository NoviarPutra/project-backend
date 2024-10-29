package com.project.bootcamp_project.dto.mapper;

import com.project.bootcamp_project.dto.SelectionStatus;
import com.project.bootcamp_project.dto.request.CreateSelectionProcessDTO;
import com.project.bootcamp_project.entity.Candidate;
import com.project.bootcamp_project.entity.SelectionProcess;
import com.project.bootcamp_project.repository.CandidateRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectionProcessMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    private Converter<String, Candidate> candidateRepositoryConverter;

    private Converter<String, SelectionStatus> selectionStatusEnumConverter;

    @PostConstruct
    public void init() {
        candidateRepositoryConverter = new Converter<String, Candidate>() {
            public Candidate convert(MappingContext<String, Candidate> context) {
                return candidateRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Candidate not found for ID: " + context.getSource()));
            }
        };
        selectionStatusEnumConverter = new Converter<String, SelectionStatus>() {
            public SelectionStatus convert(MappingContext<String, SelectionStatus> context) {
                return SelectionStatus.valueOf(context.getSource());
            }
        };
    }

    public SelectionProcess convertToEntity(CreateSelectionProcessDTO createSelectionProcessDTO) {
        modelMapper.typeMap(CreateSelectionProcessDTO.class, SelectionProcess.class).addMappings(mapper -> {
            mapper.using(candidateRepositoryConverter).map(CreateSelectionProcessDTO::getCandidateId, SelectionProcess::setCandidate);
            mapper.map(CreateSelectionProcessDTO::getRound, SelectionProcess::setRound);
            mapper.using(selectionStatusEnumConverter).map(CreateSelectionProcessDTO::getStatus, SelectionProcess::setStatus);
            mapper.map(CreateSelectionProcessDTO::getNotes, SelectionProcess::setNotes);
        });
        return modelMapper.map(createSelectionProcessDTO, SelectionProcess.class);
    }

}
