package com.taskhub.service;

import com.taskhub.dao.ProjectDao;
import com.taskhub.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    public List<ProjectEntity> getAllProjects(){
        return projectDao.findAll();
    }

    public Optional<ProjectEntity> getAProject(int projectId){
        return projectDao.findById(projectId);
    }

    public ProjectEntity addAProject(ProjectEntity newProject){
        return projectDao.saveAndFlush(newProject);
    }

    public ProjectEntity updateAProject(ProjectEntity editProject){
        return projectDao.save(editProject);
    }

    public void deleteAProject(int projectId){
        projectDao.deleteById(projectId);
    }
}
