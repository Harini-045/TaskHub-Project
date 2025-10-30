package com.taskhub.dao;

import com.taskhub.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Integer> {
    List<RoleEntity> findByRoleName(String roleName);
}
