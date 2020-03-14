package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void TestOfficerExistsById() {
        //Setup
        //Exercise
        boolean actual = jdbcOfficerDao.officerExistsById(1L);
        //Assert
        assertTrue(actual);
        //Teardown
    }

    @Test
    public void TestOfficerDoesNotExistById() {
        //Setup
        //Exercise
        boolean actual = jdbcOfficerDao.officerExistsById(0L);
        //Assert
        assertFalse(actual);
        //Teardown
    }

    @Test
    public void TestFindOfficerById() {
        //Setup
        long id = 1L;
        //Exercise
        Optional<Object> actual = jdbcOfficerDao.findOfficerById(id);
        //Assert
        assertTrue(actual.isPresent());
        Officer officer = (Officer)actual.get();
        assertEquals(id, officer.getId());
        //Teardown
    }

    @Test
    public void TestSaveOfficerToDB() {
        //Setup
        Officer expected = new Officer(6L, Rank.COMMANDER, "Spock", "");
        //Exercise
        Officer actual = jdbcOfficerDao.save(expected);
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }
}