package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.dto.UserDTO;
import com.aya.entity.Project;
import com.aya.entity.User;
import com.aya.enums.Status;
import com.aya.mapper.ProjectMapper;
import com.aya.mapper.RoleMapper;
import com.aya.mapper.UserMapper;
import com.aya.repository.ProjectRepository;
import com.aya.repository.TaskRepository;
import com.aya.service.ProjectService;
import com.aya.service.TaskService;
import com.aya.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;


    public ProjectServiceImpl(ProjectRepository projectRepository, TaskService taskService, @Lazy UserService userService, ProjectMapper projectMapper, UserMapper userMapper) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
        this.userService = userService;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
    }

    @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project=projectRepository.findByProjectCode(code);
        return projectMapper.convertToDTO(project);
//        return projectMapper.convertToDTO(projectRepository.findByProjectCode(code));
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project project=projectMapper.convertToEntity(dto);

        projectRepository.save(project);


    }

    @Override
    public void update(ProjectDTO dto) {

        //find current project
        Project project=projectRepository.findByProjectCode(dto.getProjectCode());

        //convert project to entity
        Project convertedProject=projectMapper.convertToEntity(dto);

        //set id to converted obj
        convertedProject.setId(project.getId());

        //set status of the project
        convertedProject.setProjectStatus(project.getProjectStatus());

        //save updated project
        projectRepository.save(convertedProject);




    }

    @Override
    public void delete(String code) {
        Project project=projectRepository.findByProjectCode(code);
        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode()+"-"+project.getId());

        projectRepository.save(project);

        taskService.deleteByProject(projectMapper.convertToDTO(project));

    }

    @Override
    public void complete(String code) {
        Project project=projectRepository.findByProjectCode(code);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

        taskService.completeByProject(projectMapper.convertToDTO(project));

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        //harold@manager.com
//        UserDTO currentUserDTO=userService.findByUserName("admin@admin.com");
        UserDTO currentUserDTO=userService.findByUserName(username);

        User user=userMapper.convertToEntity(currentUserDTO);

        List<Project> list=projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO obj=projectMapper.convertToDTO(project);

            obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTask(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));

            return obj;

        }).collect(Collectors.toList());

    }

    @Override
    public List<ProjectDTO> readAllByAssignedManager(User assignedManager) {
        List<Project> list=projectRepository.findAllByAssignedManager(assignedManager);
        return list.stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }


}
