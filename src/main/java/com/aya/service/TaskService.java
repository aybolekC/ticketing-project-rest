package com.aya.service;

import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.entity.User;
import com.aya.enums.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();
    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);
    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO convertToDTO);

    void completeByProject(ProjectDTO convertToDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status complete);

    void updateStatus(TaskDTO task);

    List<TaskDTO> listAllTasksByStatus(Status complete);

    List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee);
}
