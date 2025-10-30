package com.taskhub.service;

import com.taskhub.dao.LabelDao;
import com.taskhub.dao.entity.LabelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService {

    @Autowired
    LabelDao labelDao;

    public List<LabelEntity> getAllLabels(){
        return labelDao.findAll();
    }

    public Optional<LabelEntity> getALabel(int labelId){
        return labelDao.findById(labelId);
    }

    public LabelEntity addALabel(LabelEntity newLabel){
        return labelDao.saveAndFlush(newLabel);
    }

    public LabelEntity updateALabel(LabelEntity editLabel){
        return labelDao.save(editLabel);
    }

    public void deleteALabel(int labelId){
        labelDao.deleteById(labelId);
    }
}
