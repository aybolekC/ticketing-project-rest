package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.entity.Project;
import com.aya.entity.User;
import com.aya.enums.Status;
import com.aya.mapper.ProjectMapper;
import com.aya.mapper.RoleMapper;
import com.aya.repository.ProjectRepository;
import com.aya.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
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
        projectRepository.save(project);

    }

    @Override
    public void complete(String code) {
        Project project=projectRepository.findByProjectCode(code);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);

    }
}
