package com.aya.service.impl;

import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.dto.UserDTO;
import com.aya.enums.Status;
import com.aya.service.ProjectService;
import com.aya.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<String, ProjectDTO> implements ProjectService{

    TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {
        List<ProjectDTO> projectList=super.findAll().stream().filter(p->p.getAssignedManager().equals(manager))
                .map(project -> {

                    List<TaskDTO> taskList=taskService.findTasksByManager(manager);

//                     int completeTaskCounts=5;
//                     int unfinishedTaskCounts=5;


                    int completeTaskCounts=(int)taskList.stream().filter(t->t.getProject().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();
                    int unfinishedTaskCounts=(int)taskList.stream().filter(t->t.getProject().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();



                     project.setCompleteTaskCounts(completeTaskCounts);
                     project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                     return project;
                }).collect(Collectors.toList());



        return projectList;
    }
}
