package com.aya.mapper;

import com.aya.dto.TaskDTO;
import com.aya.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    //convert to entity
    public Task convertToEntity(TaskDTO dto){

        return modelMapper.map(dto, Task.class);
    }



    //convert to DTO
    public TaskDTO convertToDTO(Task entity){

        return modelMapper.map(entity, TaskDTO.class);
    }
}
