package com.galvanize.repository;

import com.galvanize.entities.Officer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcOfficerDao jdbcOfficerDao;

    @BeforeEach
    public void Setup() {
        //Setup
       jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
    }


    @Test
    public void TestConstructorDependencyInjection() {
        //Setup
        String expected = "";
        //Exercise
        String actual = "";
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void TestCountOfficers() {
        //Setup
        int expected = 5;
        //Exercise
        int actual = jdbcOfficerDao.countOfficers();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void TestFindAllOfficers() {
        //Setup
        int expected = 5;
        //Exercise
        List<Officer> officerList = jdbcOfficerDao.findAllOfficers();
        int actual = officerList.size();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }
}