package com.project.bootcamp_project.dto.mapper;


import com.project.bootcamp_project.dto.request.AdminCreateJobPositionDTO;
import com.project.bootcamp_project.dto.request.AdminUpdateJobPositionDTO;
import com.project.bootcamp_project.entity.Department;
import com.project.bootcamp_project.entity.JobPosition;
import com.project.bootcamp_project.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobPositionMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Converter<String, Department> departmentConverter;

    @PostConstruct
    public void init() {
        departmentConverter = new Converter<String, Department>() {
            public Department convert(MappingContext<String, Department> context) {
                return departmentRepository.findById(context.getSource())
                        .orElseThrow(() -> new EntityNotFoundException("Invalid Department ID: " + context.getSource()));
            }
        };
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public JobPosition convertToEntity(AdminCreateJobPositionDTO adminCreateJobPositionDTO) {
        modelMapper.typeMap(AdminCreateJobPositionDTO.class, JobPosition.class).addMappings(mapper -> {
            mapper.using(departmentConverter).map(AdminCreateJobPositionDTO::getDepartmentId, JobPosition::setDepartment);
            mapper.skip(JobPosition::setId);
        });
        return modelMapper.map(adminCreateJobPositionDTO, JobPosition.class);
    }

    public JobPosition convertToEntity(AdminUpdateJobPositionDTO adminUpdateJobPositionDTO) {
        modelMapper.typeMap(AdminUpdateJobPositionDTO.class, JobPosition.class).addMappings(mapper -> {
            mapper.using(departmentConverter).map(AdminUpdateJobPositionDTO::getDepartmentId, JobPosition::setDepartment);
            mapper.skip(JobPosition::setId);
        });
        return modelMapper.map(adminUpdateJobPositionDTO, JobPosition.class);
    }

}

