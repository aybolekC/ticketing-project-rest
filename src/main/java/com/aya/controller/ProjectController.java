package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.dto.UserDTO;
import com.aya.service.ProjectService;
import com.aya.service.RoleService;
import com.aya.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {


    UserService userService;
    RoleService roleService;
    ProjectService projectService;

    public ProjectController(UserService userService, RoleService roleService, ProjectService projectService) {
        this.userService = userService;
        this.roleService = roleService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findAllManagers());

        return "/project/create";
    }



    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project, Model model){


        projectService.save(project);

        return "redirect:/project/create";

    }
}
