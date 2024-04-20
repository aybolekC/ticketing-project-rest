package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.dto.UserDTO;
import com.aya.service.ProjectService;
import com.aya.service.RoleService;
import com.aya.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {


    private final UserService userService;
    private final RoleService roleService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, RoleService roleService, ProjectService projectService) {
        this.userService = userService;
        this.roleService = roleService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects",projectService.listAllProjectDetails());
        model.addAttribute("managers",userService.listAllByRole("manager"));

        return "/project/create";
    }



    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project){

        projectService.save(project);

        return "redirect:/project/create";

    }


    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode){
        projectService.delete(projectCode);

        return "redirect:/project/create";

    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){
        projectService.complete(projectCode);
        return "redirect:/project/create";

    }


    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project", projectService.getByProjectCode(projectCode));
        model.addAttribute("projects",projectService.listAllProjectDetails());
        model.addAttribute("managers",userService.listAllByRole("manager"));

        return "/project/update";

    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){
        projectService.update(project);

        return "redirect:/project/create";

    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model) {

        List<ProjectDTO> projects=projectService.listAllProjectDetails();
        model.addAttribute("projects", projects);

        return "/manager/project-status";
    }




    @GetMapping("/manager/complete/{projectCode}")
    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return "redirect:/project/manager/project-status";
    }

}
