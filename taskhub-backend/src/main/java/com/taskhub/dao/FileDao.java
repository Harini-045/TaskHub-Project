package com.taskhub.dao;

import com.taskhub.dao.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends JpaRepository<FileEntity, Integer> {
}
