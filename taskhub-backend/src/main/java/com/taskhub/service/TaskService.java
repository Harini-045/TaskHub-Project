package com.taskhub.service;

import com.taskhub.dao.LabelDao;
import com.taskhub.dao.TaskDao;
import com.taskhub.dao.UserDao;
import com.taskhub.dao.entity.LabelEntity;
import com.taskhub.dao.entity.TaskEntity;
import com.taskhub.dao.entity.UserEntity;
import com.taskhub.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    UserDao userDao;

    @Autowired
    LabelDao labelDao;

    public List<TaskEntity> getAllTasks(){
        return taskDao.findAll();
    }

    public Optional<TaskEntity> getATask(int taskId){
        Optional<TaskEntity> task = taskDao.findById(taskId);
        return task;
    }

    public List<TaskEntity> getAUserTask(int userId){
        List<TaskEntity> task = taskDao.findByUser_UserId(userId);
        return task;
    }

    public TaskEntity addATask(TaskEntity newtask){
        return taskDao.saveAndFlush(newtask);
    }

    public TaskEntity updateATask(TaskDTO editTaskDTO) {
        // 1️⃣ Find existing task by ID
        TaskEntity existingTask = taskDao.findById(editTaskDTO.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + editTaskDTO.getTaskId()));

        // 2️⃣ Update editable fields
        existingTask.setTaskDescription(editTaskDTO.getTaskDescription());
        existingTask.setStartDate(editTaskDTO.getStartDate());
        existingTask.setDueDate(editTaskDTO.getDueDate());
        existingTask.setPriority(editTaskDTO.getPriority());

        // 3️⃣ Update user (assignee)
        UserEntity user = userDao.findById(editTaskDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + editTaskDTO.getUserId()));
        existingTask.setUser(user);

        // 4️⃣ Update label (status)
        LabelEntity label = labelDao.findById(editTaskDTO.getLabelId())
                .orElseThrow(() -> new RuntimeException("Label not found with ID: " + editTaskDTO.getLabelId()));
        existingTask.setLabel(label);

        // 5️⃣ Save updated task
        return taskDao.save(existingTask);
    }


    public void deleteATask(int taskId){
        taskDao.deleteById(taskId);
    }
}
