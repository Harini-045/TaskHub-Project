package com.taskhub.controller;

import com.taskhub.dao.entity.ProjectEntity;
import com.taskhub.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @GetMapping()
    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
        return new ResponseEntity<List<ProjectEntity>>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @GetMapping("/{projectId}")
    public ResponseEntity<Optional<ProjectEntity>> getAProject(@PathVariable("projectId") int projectId) {
        return new ResponseEntity<Optional<ProjectEntity>>(projectService.getAProject(projectId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PostMapping("/add")
    public ResponseEntity<ProjectEntity> addAProject(@RequestBody ProjectEntity newProject) {
        return new ResponseEntity<ProjectEntity>(projectService.addAProject(newProject), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PutMapping("/update")
    public ResponseEntity<ProjectEntity> updateAProject(@RequestBody ProjectEntity editProject) {
        return new ResponseEntity<ProjectEntity>(projectService.updateAProject(editProject), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteAProject(@PathVariable("projectId") int projectId) {
        projectService.deleteAProject(projectId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
