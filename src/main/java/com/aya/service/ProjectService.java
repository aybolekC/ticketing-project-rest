package com.aya.service;

import com.aya.dto.ProjectDTO;
import com.aya.dto.UserDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO, String>{

    void complete(ProjectDTO project);

    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager);

}
