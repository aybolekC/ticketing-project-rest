package com.aya.mapper;

import com.aya.dto.RoleDTO;
import com.aya.dto.UserDTO;
import com.aya.entity.Role;
import com.aya.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {


    private final ModelMapper modelMapper;


    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //convert to entity
    public User convertToEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }



    //convert to DTO
    public UserDTO convertToDTO(User entity){
        return modelMapper.map(entity, UserDTO.class);
    }

}
