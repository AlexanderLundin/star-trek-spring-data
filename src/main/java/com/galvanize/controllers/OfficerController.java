package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repository.JdbcOfficerDao;
import com.galvanize.repository.JpaOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@RestController
public class OfficerController {


    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcOfficerDao jdbcOfficerDao;
    JpaOfficerDao jpaOfficerDao;


    public OfficerController(JdbcTemplate jdbcTemplate, JpaOfficerDao jpaOfficerDao){
        jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
        this.jpaOfficerDao = jpaOfficerDao;
    }

    @GetMapping("/officers")
    public List<Officer> JDBCGetAllOfficers() {
        List<Officer> officerList = jdbcOfficerDao.findAllOfficers();
        return officerList;
    }

    @GetMapping("/officers/{id}")
    public Optional<Object> JDBCGetOfficerByID(@PathVariable Long id){
        Optional<Object> officer = jdbcOfficerDao.findOfficerById(id);
        return officer;
    }

    // CREATE
    @PostMapping("/officers")
    public Officer JDBCPostOfficer(@RequestBody Officer officer){
        return jdbcOfficerDao.save(officer);
    }

    //UPDATE
    @PatchMapping("/officers/{id}/{rank}")
    public Officer JPAPatchOfficerRank(@PathVariable Long id, @PathVariable Rank rank){
        return jpaOfficerDao.updateRankByID(id, rank);
    }

    // DELETE
    @DeleteMapping("/officers/{id}")
    public void JBDCDeleteOfficerByID( @PathVariable Long id){
        jdbcOfficerDao.delete(id);
    }

}
