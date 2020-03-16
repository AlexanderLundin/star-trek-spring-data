package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.repository.JdbcOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
