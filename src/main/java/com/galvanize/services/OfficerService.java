package com.galvanize.services;

import com.galvanize.entities.Officer;
import com.galvanize.repositories.JpaOfficerDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficerService {

    private JpaOfficerDao jpaDao;

    public OfficerService (JpaOfficerDao jpaDao){
        this.jpaDao = jpaDao;
    }

    public Officer save (Officer officer){
        return jpaDao.save(officer);
    }

    public List<Officer> findAll() {
        return jpaDao.findAll();
    }
}
