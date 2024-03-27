package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<String, ProjectDTO> implements ProjectService{


    @Override
    public ProjectDTO save(ProjectDTO object) {
        return super.save(object.getProjectCode(), object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {
        super.update(object.getProjectCode(), object);

    }

    @Override
    public void deleteById(String projectCode) {

        super.deleteById(projectCode);

    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }
}
