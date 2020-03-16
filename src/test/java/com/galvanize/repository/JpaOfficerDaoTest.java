package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaOfficerDAOTest{
    @Autowired
    private JpaOfficerDao dao;

    @Autowired
    private JdbcTemplate template;

    @Test
    public void TestCount() throws Exception {
        assertEquals(5, dao.count());
    }

    @Test
    public void TestFindAll() throws Exception {
        List<String> expected = Arrays.asList("Picard", "Sisko", "Janeway", "Archer", "Kirk");
        List<String> actual = dao.findAll().stream()
                .map(Officer::getLast)
                .collect(Collectors.toList());
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }


    @Test
    public void TestExistsByIdValid() throws Exception {
        boolean actual = dao.existsById(1L);
        assertTrue(actual);
    }

    @Test
    public void TestExistsByIdInvalid() throws Exception {
        boolean actual = dao.existsById(999L);
        assertFalse(actual);
    }

    @Test
    public void TestFindByID() {
        //Setup
        long expected = 2L;
        //Exercise
        Officer actual = dao.findById(expected);
        //Assert
        assertNotNull(actual.getId());
        //Teardown
    }

    @Test
    public void TestFindByIDInvalid() {
        //Setup
        long expected = 999L;
        //Exercise
        Officer actual = dao.findById(expected);
        //Assert
        assertNull(actual);
        //Teardown
    }
}