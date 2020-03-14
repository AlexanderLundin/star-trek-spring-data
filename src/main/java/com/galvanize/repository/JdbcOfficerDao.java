package com.galvanize.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOfficerDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficerDao(){
        jdbcTemplate = new JdbcTemplate();
    }
}
