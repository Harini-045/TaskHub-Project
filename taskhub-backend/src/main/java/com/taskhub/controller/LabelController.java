package com.taskhub.controller;

import com.taskhub.dao.entity.LabelEntity;
import com.taskhub.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @PreAuthorize("hasAnyRole('Admin', 'Manager', 'Developer')")
    @GetMapping()
    public ResponseEntity<List<LabelEntity>> getAllLabels() {
        return new ResponseEntity<List<LabelEntity>>(labelService.getAllLabels(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @GetMapping("/{labelId}")
    public ResponseEntity<Optional<LabelEntity>> getALabel(@PathVariable("labelId") int labelId) {
        return new ResponseEntity<Optional<LabelEntity>>(labelService.getALabel(labelId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PostMapping("/add")
    public ResponseEntity<LabelEntity> addALabel(@RequestBody LabelEntity newLabel) {
        return new ResponseEntity<LabelEntity>(labelService.addALabel(newLabel), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @PutMapping("/update")
    public ResponseEntity<LabelEntity> updateALabel(@RequestBody LabelEntity editLabel) {
        return new ResponseEntity<LabelEntity>(labelService.updateALabel(editLabel), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Manager')")
    @DeleteMapping("/{labelId}")
    public ResponseEntity<Void> deleteALabel(@PathVariable("labelId") int labelId) {
        labelService.deleteALabel(labelId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
