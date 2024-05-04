package com.aya.controller;


import com.aya.dto.TaskDTO;
import com.aya.entity.ResponseWrapper;
import com.aya.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getTasks(){
        List<TaskDTO> taskDTOList = taskService.listAllTasks();
        return ResponseEntity.ok(new ResponseWrapper("Task are successfully retrieved",taskDTOList, HttpStatus.OK));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable("taskId") Long taskId){
        TaskDTO taskDTO = taskService.findById(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved",taskDTO,HttpStatus.OK));
    }


    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO){
        taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created",HttpStatus.CREATED));

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long taskId){
        taskService.delete(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully deleted",HttpStatus.OK));
    }

//    @GetMapping("/create")
//    public String createTask(Model model){
//
//
//        model.addAttribute("task", new TaskDTO());
//        model.addAttribute("projects",projectService.listAllProjects());
//        model.addAttribute("employees",userService.listAllByRole("employee"));
//        model.addAttribute("tasks",taskService.listAllTasks());
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
//        taskService.delete(taskId);
//
//        return "redirect:/task/create";
//
//    }
//
//    @GetMapping("/update/{taskId}")
//    public String editTask(@PathVariable("taskId") Long taskId, Model model){
//
//        model.addAttribute("task", taskService.findById(taskId));
//        model.addAttribute("projects",projectService.listAllProjects());
//        model.addAttribute("employees",userService.listAllByRole("employee"));
//        model.addAttribute("tasks",taskService.listAllTasks());
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
////
//    //when the path variable has the same name as object field in DTO, then springboot will automatically
//    //bind it(set path variable to the corresponding field )... the name of the field should be exactly the same
////    @PostMapping("/update/{id}")
////    public String updateTask(TaskDTO task){
////        taskService.update(task);
////        return "redirect:/task/create";
////
////    }
////
////    @GetMapping("/employee/pending-tasks")
////    public String employeePendingTasks(Model model) {
////        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));
////        return "task/pending-tasks";
////    }
////
////
////    @GetMapping("/employee/archive")
////    public String employeeArchivedTasks(Model model) {
////        model.addAttribute("tasks", taskService.listAllTasksByStatus(Status.COMPLETE));
////        return "task/archive";
////    }
////
////    @GetMapping("/employee/edit/{id}")
////    public String employeeEditTask(@PathVariable("id") Long id, Model model) {
////
////        model.addAttribute("task", taskService.findById(id));
////        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));
////        model.addAttribute("statuses", Status.values());
////
////        return "task/status-update";
////
////    }
////
////    @PostMapping("/employee/update/{id}")
////    public String employeeUpdateTask(@ModelAttribute("task") TaskDTO task) {
////
////        taskService.updateStatus(task);
////
////        taskService.findById(task.getId()).setProject(task.getProject());
////        return "redirect:/task/employee/pending-tasks";
////
////    }


}
