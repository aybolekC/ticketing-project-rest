package com.aya.mapper;


import com.aya.dto.ProjectDTO;
import com.aya.dto.RoleDTO;
import com.aya.entity.Project;
import com.aya.entity.Role;
import com.aya.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    private final ModelMapper modelMapper;


    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convert to entity
    public Project convertToEntity(ProjectDTO dto){

        return modelMapper.map(dto, Project.class);
    }



    //convert to DTO
    public ProjectDTO convertToDTO(Project entity){

        return modelMapper.map(entity, ProjectDTO.class);
    }


}
