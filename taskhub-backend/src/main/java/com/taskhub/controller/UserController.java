package com.taskhub.controller;

import com.taskhub.dao.entity.TeamUserEntity;
import com.taskhub.dao.entity.UserEntity;
import com.taskhub.dto.UserDTO;
import com.taskhub.service.TeamUserService;
import com.taskhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TeamUserService teamUserService;

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping()
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return new ResponseEntity<List<UserEntity>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserEntity>> getAUser(@PathVariable ("userId") int userId){
        return new ResponseEntity<Optional<UserEntity>>(userService.getAUser(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateAUser(@RequestBody UserDTO editUser) {
        return new ResponseEntity<UserEntity>(userService.updateAUser(editUser), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserDTO userDTO) {
        UserEntity savedUser = userService.addAUser(userDTO);
        return new ResponseEntity<UserEntity>(savedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteAUser(@PathVariable("userId") int userId) {
        userService.deleteAUser(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<UserEntity>> getUsersByTeam(@PathVariable int teamId) {
        List<UserEntity> users = teamUserService.getUsersByTeamId(teamId);
        return ResponseEntity.ok(users);
    }
}
