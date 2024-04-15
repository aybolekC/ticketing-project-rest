package com.aya.mapper;

import com.aya.dto.RoleDTO;
import com.aya.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    //convert to entity
    public Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }



    //convert to DTO
    public RoleDTO convertToDTO(Role entity){
        return modelMapper.map(entity, RoleDTO.class);
    }


}
