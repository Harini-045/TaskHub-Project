package com.taskhub.dao;

import com.taskhub.dao.entity.TeamUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserDao extends JpaRepository<TeamUserEntity, Integer> {
    List<TeamUserEntity> findByTeam_TeamId(int teamId);
}
