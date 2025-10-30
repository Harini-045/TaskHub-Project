package com.taskhub.dao;

import com.taskhub.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
