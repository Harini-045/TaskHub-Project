package com.taskhub.controller;

import com.taskhub.dao.entity.FileEntity;
import com.taskhub.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping()
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        return new ResponseEntity<List<FileEntity>>(fileService.getALlFiles(), HttpStatus.OK);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Optional<FileEntity>> getAFile(@PathVariable("fileId") int fileId) {
        return new ResponseEntity<Optional<FileEntity>>(fileService.getAFile(fileId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FileEntity> addAFile(@RequestBody FileEntity newFile) {
        return new ResponseEntity<FileEntity>(fileService.addAFile(newFile), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<FileEntity> updateAFile(@RequestBody FileEntity editFile) {
        return new ResponseEntity<FileEntity>(fileService.updateAFile(editFile), HttpStatus.OK);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteAFile(@PathVariable("fileId") int fileId) {
        fileService.deleteAFile(fileId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
