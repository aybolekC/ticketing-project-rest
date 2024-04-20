package com.aya.mapper;


import com.aya.dto.ProjectDTO;
import com.aya.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    public <T> T convert(Object objectToBeConverted, T convertedObject){
        return modelMapper.map(objectToBeConverted,(Type)convertedObject.getClass());
    }



//    //convert to entity
//    public <T> T convertToEntity(Object objectToBeConverted, T convertedObject){
//
//        return modelMapper.map(objectToBeConverted,(Type)convertedObject.getClass());
//    }
//
//
//
//    //convert to DTO
//    public <T> T convertToDTO(Object objectToBeConverted, T convertedObject){
//
//        return modelMapper.map(objectToBeConverted,(Type)convertedObject.getClass());
//    }


}
