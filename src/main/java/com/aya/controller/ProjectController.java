package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.entity.ResponseWrapper;
import com.aya.entity.User;
import com.aya.service.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public ResponseEntity<ResponseWrapper> getProjects() {
        List<ProjectDTO> projects = projectService.listAllProjects();
        return ResponseEntity.ok(
                new ResponseWrapper("Projects are successfully retrieved", projects, HttpStatus.OK));
    }

    @GetMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectCode") String projectCode) {
        ProjectDTO project = projectService.getByProjectCode(projectCode);

        return ResponseEntity.ok(
                new ResponseWrapper("Project is successfully retrieved", project, HttpStatus.OK));
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project) {
        projectService.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Project is successfully created", HttpStatus.CREATED));
    }

    @PutMapping()
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project) {
        projectService.update(project);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Project is successfully updated", project, HttpStatus.CREATED));
    }

    @DeleteMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.delete(projectCode);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));
    }


    @GetMapping("/manager/project/{username}")
    public ResponseEntity<ResponseWrapper> getProjectByManager(@PathVariable("user") User user) {
        List<ProjectDTO> projectsAssignedToManager = projectService.readAllByAssignedManager(user);

        return ResponseEntity.ok(
                new ResponseWrapper("Projects assigned to this manager are successfully retrieved",
                        projectsAssignedToManager, HttpStatus.OK));
    }

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> completeProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Project is successfully completed", HttpStatus.CREATED));
    }

}
