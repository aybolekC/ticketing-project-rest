package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.entity.ResponseWrapper;
import com.aya.entity.User;
import com.aya.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@Tag(name = "ProjectController", description = "Project API")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    @RolesAllowed({"Admin","Manager"})
    @Operation(summary = "Get Projects")
    public ResponseEntity<ResponseWrapper> getProjects() {
        List<ProjectDTO> projects = projectService.listAllProjects();
        return ResponseEntity.ok(
                new ResponseWrapper("Projects are successfully retrieved", projects, HttpStatus.OK));
    }

    @GetMapping("/{projectCode}")
    @RolesAllowed("Manager")
    @Operation(summary = "Get Project By ProjectCode")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("projectCode") String projectCode) {
        ProjectDTO project = projectService.getByProjectCode(projectCode);

        return ResponseEntity.ok(
                new ResponseWrapper("Project is successfully retrieved", project, HttpStatus.OK));
    }


    @PostMapping()
    @RolesAllowed("Manager")
    @Operation(summary = "Create Project")
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project) {
        projectService.save(project);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("Project is successfully created", HttpStatus.CREATED));
    }

    @PutMapping()
    @RolesAllowed("Manager")
    @Operation(summary = "Update Project")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project) {
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", project, HttpStatus.OK));
    }

    @DeleteMapping("/{projectCode}")
    @RolesAllowed("Manager")
    @Operation(summary = "Delete Project")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.delete(projectCode);

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));
    }


    @GetMapping("/manager/project-status")
    @RolesAllowed("Manager")
    @Operation(summary = "Create Projects By Manager")
    public ResponseEntity<ResponseWrapper> getProjectByManager() {
        List<ProjectDTO> projectsAssignedToManager = projectService.listAllProjectDetails();

        return ResponseEntity.ok(
                new ResponseWrapper("Projects assigned to this manager are successfully retrieved",
                        projectsAssignedToManager, HttpStatus.OK));
    }

    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed("Manager")
    @Operation(summary = "Complete Project")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed", HttpStatus.OK));
    }

}
