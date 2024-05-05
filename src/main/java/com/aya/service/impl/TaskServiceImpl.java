package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.entity.Task;
import com.aya.entity.User;
import com.aya.enums.Status;
import com.aya.mapper.ProjectMapper;
import com.aya.mapper.TaskMapper;
import com.aya.repository.TaskRepository;
import com.aya.repository.UserRepository;
import com.aya.service.TaskService;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;


    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task=taskRepository.findById(id);
        if (task.isPresent()){
            return taskMapper.convertToDTO(task.get());
        }
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task=taskMapper.convertToEntity(dto);
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO dto) {
        //find current task
        Optional<Task> task=taskRepository.findById(dto.getId());

        //convert task to entity
        Task convertedTask=taskMapper.convertToEntity(dto);

        if(task.isPresent()){
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(dto.getTaskStatus()==null ? task.get().getTaskStatus() : dto.getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public void delete(Long id) {
        Optional<Task> task=taskRepository.findById(id);

        if(task.isPresent()){
            task.get().setIsDeleted(true);
            taskRepository.save(task.get());

        }
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {

        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {

        return taskRepository.totalCompletedTasks(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {

        List<TaskDTO> list=listAllByProject(projectDTO);
        list.forEach(taskDTO -> delete(taskDTO.getId()));

    }

    @Override
    public void completeByProject(ProjectDTO projectDTO) {

        List<TaskDTO> list=listAllByProject(projectDTO);
        list.forEach(taskDTO -> {
            taskDTO.setTaskStatus(Status.COMPLETE);
            update(taskDTO);
        });

    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status complete) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount details=(SimpleKeycloakAccount) authentication.getDetails();
        String username= details.getKeycloakSecurityContext().getToken().getPreferredUsername();

//        String username= SecurityContextHolder.getContext().getAuthentication().getName();
//
        //sameen@employee.com
//       User loggedInUser=userRepository.findByUserName("sameen@employee.com");
        User loggedInUser=userRepository.findByUserName(username);
        List<Task> list=taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(complete, loggedInUser);


        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO dto) {

        Optional<Task> task=taskRepository.findById(dto.getId());

        if (task.isPresent()){
            task.get().setTaskStatus(dto.getTaskStatus());
            taskRepository.save(task.get());
        }

    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status complete) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount details=(SimpleKeycloakAccount) authentication.getDetails();
        String username= details.getKeycloakSecurityContext().getToken().getPreferredUsername();

//        String username= SecurityContextHolder.getContext().getAuthentication().getName();
//
//        //sameen@employee.com
//        User loggedInUser=userRepository.findByUserName("sameen@employee.com");
        User loggedInUser=userRepository.findByUserName(username);
        List<Task> list=taskRepository.findAllByTaskStatusIsAndAssignedEmployee(complete, loggedInUser);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> readAllByAssignedEmployee(User assignedEmployee) {
        List<Task> list=taskRepository.findAllByAssignedEmployee(assignedEmployee);
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    private List<TaskDTO> listAllByProject(ProjectDTO projectDTO){
        List<Task> list=taskRepository.findAllByProject(projectMapper.convertToEntity(projectDTO));
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

}
