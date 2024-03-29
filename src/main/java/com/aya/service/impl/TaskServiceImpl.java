package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.enums.Status;
import com.aya.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl extends AbstractMapService<Long, TaskDTO> implements TaskService {

    @Override
    public TaskDTO save(TaskDTO object) {

        if (object.getTaskStatus()==null){
            object.setTaskStatus(Status.OPEN);
        }
//
//        if (object.getAssignedDate()==null){
//            object.setAssignedDate(LocalDate.now());
//        }

        return super.save(object.getId(),object);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO object) {

        super.update(object.getId(), object);

    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }
}
