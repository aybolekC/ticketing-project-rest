package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.dto.UserDTO;
import com.aya.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
//
//
//    UserService userService;
//    RoleService roleService;
//    ProjectService projectService;
//
//    public ProjectController(UserService userService, RoleService roleService, ProjectService projectService) {
//        this.userService = userService;
//        this.roleService = roleService;
//        this.projectService = projectService;
//    }
//
//    @GetMapping("/create")
//    public String createProject(Model model){
//
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("managers",userService.findAllManagers());
//
//        return "/project/create";
//    }
//
//
//
//    @PostMapping("/create")
//    public String insertProject(@ModelAttribute("project") ProjectDTO project){
//
//        projectService.save(project);
//
//        return "redirect:/project/create";
//
//    }
//
//
//    @GetMapping("/delete/{projectCode}")
//    public String deleteProject(@PathVariable("projectCode") String projectCode){
//        projectService.deleteById(projectCode);
//
//        return "redirect:/project/create";
//
//    }
//
//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode){
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/create";
//
//    }
//
//
//    @GetMapping("/update/{projectCode}")
//    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
//
//        model.addAttribute("project", projectService.findById(projectCode));
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("managers",userService.findAllManagers());
//
//        return "/project/update";
//
//    }
//
//    @PostMapping("/update")
//    public String updateProject(@ModelAttribute("project") ProjectDTO project){
//        projectService.update(project);
//
//        return "redirect:/project/create";
//
//    }
//
//    @GetMapping("/manager/project-status")
//    public String getProjectByManager(Model model) {
//
//        UserDTO manager=userService.findById("john@gmail.com");
//
//        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);
//
//        model.addAttribute("projects", projects);
//
//        return "/manager/project-status";
//    }
//
//    @GetMapping("/manager/complete/{projectCode}")
//    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/manager/project-status";
//    }

}
