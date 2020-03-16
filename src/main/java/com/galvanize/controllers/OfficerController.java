package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.repository.JdbcOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OfficerController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcOfficerDao jdbcOfficerDao;

    public OfficerController(JdbcTemplate jdbcTemplate){
        jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
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

}
