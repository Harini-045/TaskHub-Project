package com.taskhub.service;

import com.taskhub.dao.RoleDao;
import com.taskhub.dao.TeamDao;
import com.taskhub.dao.TeamUserDao;
import com.taskhub.dao.UserDao;
import com.taskhub.dao.entity.RoleEntity;
import com.taskhub.dao.entity.TeamEntity;
import com.taskhub.dao.entity.TeamUserEntity;
import com.taskhub.dao.entity.UserEntity;
import com.taskhub.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    TeamUserDao teamUserDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers(){
        return userDao.findAll();
    }

    public Optional<UserEntity> getAUser(int userId){
        return userDao.findById(userId);
    }

    public UserEntity addAUser(UserDTO newUser){
        UserEntity user = new UserEntity();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setUserPassword(passwordEncoder.encode(newUser.getUserPassword()));

        // fetch roles from DB
        List<RoleEntity> roles = roleDao.findAllById(newUser.getRoleIds());
        user.setAllRoles(roles);

        return userDao.saveAndFlush(user);
    }

    public UserEntity updateAUser(UserDTO editUser){
        UserEntity existingUser = userDao.findByEmail(editUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with Email: " + editUser.getEmail()));
        existingUser.setUsername(editUser.getUsername());
        existingUser.setEmail(editUser.getEmail());
        if (editUser.getUserPassword() != null && !editUser.getUserPassword().isEmpty()) {
            existingUser.setUserPassword(passwordEncoder.encode(editUser.getUserPassword()));
        }
        if (editUser.getRoleIds() != null && !editUser.getRoleIds().isEmpty()) {
            List<RoleEntity> roles = roleDao.findAllById(editUser.getRoleIds());
            existingUser.setAllRoles(roles);
        }

        return userDao.save(existingUser);
    }

    public List<TeamUserEntity> getAllTeamUsers(){
        return teamUserDao.findAll();
    }

    public void deleteAUser(int userId){
        userDao.deleteById(userId);
    }

}
