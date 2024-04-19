package com.aya.service;

import com.aya.dto.ProjectDTO;
import com.aya.entity.User;

import java.util.List;

public interface ProjectService {


    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO dto);
    void update(ProjectDTO dto);
    void delete(String code);
    void complete(String code);
    List<ProjectDTO> listAllProjectDetails();


    List<ProjectDTO> readAllByAssignedManager(User assignedManager);
}
