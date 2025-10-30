package com.taskhub.service;

import com.taskhub.dao.RoleDao;
import com.taskhub.dao.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public List<RoleEntity> getAllRoles(){
        return roleDao.findAll();
    }

    public Optional<RoleEntity> getARole(int roleId){
        return roleDao.findById(roleId);
    }

    public RoleEntity addARole(RoleEntity newRole){
        return roleDao.saveAndFlush(newRole);
    }

    public RoleEntity updateARole(RoleEntity editRole){
        return roleDao.save(editRole);
    }

    public void deleteARole(int roleId){
        roleDao.deleteById(roleId);
    }
}
