package com.taskhub.service;

import com.taskhub.dao.TeamUserDao;
import com.taskhub.dao.entity.TeamUserEntity;
import com.taskhub.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamUserService {

    @Autowired
    TeamUserDao teamUserDao;

    public List<TeamUserEntity> getAllTeamUsers(){
        return teamUserDao.findAll();
    }

    public Optional<TeamUserEntity> getATeamUser(int teamId){
        return teamUserDao.findById(teamId);
    }

    public TeamUserEntity addATeamUser(TeamUserEntity newTeamUser){
        return teamUserDao.saveAndFlush(newTeamUser);
    }

    public TeamUserEntity updateATeamUser(TeamUserEntity editTeamUser){
        return teamUserDao.save(editTeamUser);
    }

    public void deleteATeamUser(int teamId){
        teamUserDao.deleteById(teamId);
    }


    public List<UserEntity> getUsersByTeamId(int teamId) {
        List<TeamUserEntity> joins = teamUserDao.findByTeam_TeamId(teamId);
        return joins.stream()
                .map(TeamUserEntity::getUser)
                .collect(Collectors.toList());
    }

}
