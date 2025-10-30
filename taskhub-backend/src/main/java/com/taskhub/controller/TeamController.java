package com.taskhub.controller;

import com.taskhub.dao.entity.TeamEntity;
import com.taskhub.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @GetMapping()
    public ResponseEntity<List<TeamEntity>> getAllTeams() {
        return new ResponseEntity<List<TeamEntity>>(teamService.getAllTeams(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'ROLE_Developer')")
    @GetMapping("/{teamId}")
    public ResponseEntity<Optional<TeamEntity>> getATeam(@PathVariable("teamId") int teamId) {
        System.out.println("Controller: Auth = " + SecurityContextHolder.getContext().getAuthentication());

        return new ResponseEntity<Optional<TeamEntity>>(teamService.getATeam(teamId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PostMapping("/add")
    public ResponseEntity<TeamEntity> addATeam(@RequestBody TeamEntity newTeam) {
        return new ResponseEntity<TeamEntity>(teamService.addATeam(newTeam), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PutMapping("/update")
    public ResponseEntity<TeamEntity> updateATeam(@RequestBody TeamEntity editTeam) {
        return new ResponseEntity<TeamEntity>(teamService.updateATeam(editTeam), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteATeam(@PathVariable("teamId") int teamId) {
        teamService.deleteATeam(teamId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
