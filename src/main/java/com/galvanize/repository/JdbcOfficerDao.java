package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcOfficerDao {

    private JdbcTemplate jdbcTemplate;
    private final String COUNT_OFFICERS = "select count(*) from officers";


    public JdbcOfficerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countOfficers() {
        return jdbcTemplate.queryForObject(COUNT_OFFICERS, Integer.class);
    }
}
