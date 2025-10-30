package com.taskhub.controller;

import com.taskhub.dao.entity.RoleEntity;
import com.taskhub.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    @GetMapping()
    public ResponseEntity<List<RoleEntity>> getAllRoles() {
        return new ResponseEntity<List<RoleEntity>>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @GetMapping("/{roleId}")
    public ResponseEntity<Optional<RoleEntity>> getARole(@PathVariable("roleId") int roleId) {
        return new ResponseEntity<Optional<RoleEntity>>(roleService.getARole(roleId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @PostMapping("/add")
    public ResponseEntity<RoleEntity> addARole(@RequestBody RoleEntity newRole) {
        return new ResponseEntity<RoleEntity>(roleService.addARole(newRole), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @PutMapping("/update")
    public ResponseEntity<RoleEntity> updateARole(@RequestBody RoleEntity editRole) {
        return new ResponseEntity<RoleEntity>(roleService.updateARole(editRole), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin')")
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteARole(@PathVariable("roleId") int roleId) {
        roleService.deleteARole(roleId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
