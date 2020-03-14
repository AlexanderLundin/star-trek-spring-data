package com.galvanize.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void TestTest1() {
        //Setup
        String expected = "";
        //Exercise
        String actual = "";
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

}