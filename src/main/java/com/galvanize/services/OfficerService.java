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


    // CREATE


    public Officer save (Officer officer){
        return jpaDao.save(officer);
    }


    // READ


    public List<Officer> findAll() {
        return jpaDao.findAll();
    }

    public Officer findById(Long id) {
        if (jpaDao.existsById(id)){
            return jpaDao.findById(id).get();
        }else{
            return null;
        }

    }


    // UPDATE


    public Officer updateById(Long id, Officer officer) {
        if (jpaDao.existsById(id)){
            return jpaDao.save(officer);
        }else {
            throw new RuntimeException ("Officer not found");
        }
    }


    // DELETE


    public void deleteById(Long id) {
        if (jpaDao.existsById(id)){
            jpaDao.deleteById(id);
        }else {
            throw new RuntimeException ("Officer not found");
        }
    }



}
