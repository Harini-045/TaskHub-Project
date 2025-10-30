package com.taskhub.controller;

import com.taskhub.dao.entity.TaskEntity;
import com.taskhub.dto.TaskDTO;
import com.taskhub.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
        System.out.println("Controller: Auth = " + SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<List<TaskEntity>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping("/{taskId}")
    public ResponseEntity<Optional<TaskEntity>> getATask(@PathVariable("taskId") int taskId){
        return new ResponseEntity<Optional<TaskEntity>>(taskService.getATask(taskId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskEntity>> getAUserTask(@PathVariable("userId") int userId){
        return new ResponseEntity<List<TaskEntity>>(taskService.getAUserTask(userId), HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @PostMapping("/add")
    public ResponseEntity<TaskEntity> addATask(@RequestBody TaskEntity newTask){
        return new ResponseEntity<TaskEntity>(taskService.addATask(newTask), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @PutMapping("/update")
    public ResponseEntity<TaskEntity> updateATask(@RequestBody TaskDTO editTask){
        return new ResponseEntity<TaskEntity>(taskService.updateATask(editTask), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteATask(@PathVariable ("taskId") int taskId){
        taskService.deleteATask(taskId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
