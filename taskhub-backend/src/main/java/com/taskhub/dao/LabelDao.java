package com.taskhub.dao;

import com.taskhub.dao.entity.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelDao extends JpaRepository<LabelEntity, Integer> {
}
