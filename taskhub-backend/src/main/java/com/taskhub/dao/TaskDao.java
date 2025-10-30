package com.taskhub.dao;

import com.taskhub.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, Integer>{
    List<TaskEntity> findByUser_UserId(int userId);
}
