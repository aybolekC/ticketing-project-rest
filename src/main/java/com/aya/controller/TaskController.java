package com.aya.controller;


import com.aya.dto.ProjectDTO;
import com.aya.dto.TaskDTO;
import com.aya.dto.UserDTO;
import com.aya.service.ProjectService;
import com.aya.service.RoleService;
import com.aya.service.TaskService;
import com.aya.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    UserService userService;
    ProjectService projectService;
    TaskService taskService;
    RoleService roleService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService, RoleService roleService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String createTask(Model model){


        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findAllEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";

    }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){
        taskService.deleteById(id);

        return "redirect:/task/create";

    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findAllEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";

    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute("task") TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";

    }


}
