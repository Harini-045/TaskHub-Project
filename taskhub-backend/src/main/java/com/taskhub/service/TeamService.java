package com.taskhub.service;

import com.taskhub.dao.TeamDao;
import com.taskhub.dao.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;

    public List<TeamEntity> getAllTeams(){
        return teamDao.findAll();
    }

    public Optional<TeamEntity> getATeam(int teamId){
        return teamDao.findById(teamId);
    }

    public TeamEntity addATeam(TeamEntity newTeam){
        return teamDao.saveAndFlush(newTeam);
    }

    public TeamEntity updateATeam(TeamEntity editTeam){
        return teamDao.save(editTeam);
    }

    public void deleteATeam(int teamId){
        teamDao.deleteById(teamId);
    }


}
