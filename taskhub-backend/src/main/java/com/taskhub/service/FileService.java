package com.taskhub.service;

import com.taskhub.dao.FileDao;
import com.taskhub.dao.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    FileDao fileDao;

    public List<FileEntity> getALlFiles(){
        return fileDao.findAll();
    }

    public Optional<FileEntity> getAFile(int fileId){
        return fileDao.findById(fileId);
    }

    public FileEntity addAFile(FileEntity newFile){
        return fileDao.saveAndFlush(newFile);
    }

    public FileEntity updateAFile(FileEntity editFile){
        return fileDao.save(editFile);
    }

    public void deleteAFile(int fileId){
        fileDao.deleteById(fileId);
    }
}
