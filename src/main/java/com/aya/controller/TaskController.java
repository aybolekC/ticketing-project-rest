package com.aya.controller;


import com.aya.dto.TaskDTO;
import com.aya.enums.Status;
import com.aya.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

//    UserService userService;
//    ProjectService projectService;
//    TaskService taskService;
//    RoleService roleService;
//
//    public TaskController(UserService userService, ProjectService projectService, TaskService taskService, RoleService roleService) {
//        this.userService = userService;
//        this.projectService = projectService;
//        this.taskService = taskService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping("/create")
//    public String createTask(Model model){
//
//
//        model.addAttribute("task", new TaskDTO());
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("employees",userService.findAllEmployees());
//        model.addAttribute("tasks",taskService.findAll());
//
//        return "/task/create";
//    }
//
//    @PostMapping("/create")
//    public String insertTask(@ModelAttribute("task") TaskDTO task){
//
//        taskService.save(task);
//
//        return "redirect:/task/create";
//
//    }
//
//
//    @GetMapping("/delete/{taskId}")
//    public String deleteTask(@PathVariable("taskId") Long taskId){
//        taskService.deleteById(taskId);
//
//        return "redirect:/task/create";
//
//    }
//
//    @GetMapping("/update/{taskId}")
//    public String editTask(@PathVariable("taskId") Long taskId, Model model){
//
//        model.addAttribute("task", taskService.findById(taskId));
//        model.addAttribute("projects",projectService.findAll());
//        model.addAttribute("employees",userService.findAllEmployees());
//        model.addAttribute("tasks",taskService.findAll());
//
//        return "/task/update";
//
//    }
//
////    @PostMapping("/update/{taskId}")
////    public String updateTask(@PathVariable("taskId") @ModelAttribute("task") Long taskId, TaskDTO task){
////
////        task.setId(taskId);
////
////        taskService.update(task);
////
////        return "redirect:/task/create";
////
////    }
//
//
//    //when the path variable has the same name as object field in DTO, then springboot will automatically
//    //bind it(set path variable to the corresponding field )... the name of the field should be exactly the same
//    @PostMapping("/update/{id}")
//    public String updateTask(TaskDTO task){
//        taskService.update(task);
//        return "redirect:/task/create";
//
//    }
//
//    @GetMapping("/employee/pending-tasks")
//    public String employeePendingTasks(Model model) {
//        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
//        return "task/pending-tasks";
//    }
//
//
//    @GetMapping("/employee/archive")
//    public String employeeArchivedTasks(Model model) {
//        model.addAttribute("tasks", taskService.findAllTasksByStatus(Status.COMPLETE));
//        return "task/archive";
//    }
//
//    @GetMapping("/employee/edit/{id}")
//    public String employeeEditTask(@PathVariable("id") Long id, Model model) {
//
//        model.addAttribute("task", taskService.findById(id));
//        model.addAttribute("tasks", taskService.findAllTasksByStatusIsNot(Status.COMPLETE));
//        model.addAttribute("statuses", Status.values());
//
//        return "task/status-update";
//
//    }
//
//    @PostMapping("/employee/update/{id}")
//    public String employeeUpdateTask(@ModelAttribute("task") TaskDTO task) {
//
//        taskService.updateStatus(task);
//
//        taskService.findById(task.getId()).setProject(task.getProject());
//        return "redirect:/task/employee/pending-tasks";
//
//    }


}
