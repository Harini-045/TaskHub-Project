package com.taskhub.dao;

import com.taskhub.dao.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamDao extends JpaRepository<TeamEntity, Integer> {
}
