package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.enums.Status;
import com.aya.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<String, ProjectDTO> implements ProjectService{


    @Override
    public ProjectDTO save(ProjectDTO object) {

        if(object.getProjectStatus()==null){
            object.setProjectStatus(Status.OPEN);
        }
        return super.save(object.getProjectCode(), object);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(ProjectDTO object) {

         ProjectDTO newProject=findById(object.getProjectCode());

        if(object.getProjectStatus()==null){

            object.setProjectStatus(newProject.getProjectStatus());
        }
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


    @Override
    public void complete(ProjectDTO project) {

      project.setProjectStatus(Status.COMPLETE);

      super.save(project.getProjectCode(), project);
    }
}
