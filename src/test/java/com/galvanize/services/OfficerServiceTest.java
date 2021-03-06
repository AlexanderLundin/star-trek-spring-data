package com.galvanize.services;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class OfficerServiceTest {


    @Autowired
    OfficerService officerService;


    private Officer officer;
    private String first = "James II";
    private String last = "Kirk";
    private Rank rank = Rank.COMMANDER;

    @BeforeEach
    public void setup() {
        //Setup
        officer = new Officer(rank, first, last);
        officerService.save(officer);
    }


    // CREATE ,  covered by setup


    // READ


    @Test
    public void testFindAllOfficers() {
        //Setup
        int notExpected = 0;
        //Exercise
        List<Officer> officers = officerService.findAll();
        int actual = officers.size();
        //Assert
        assertNotEquals(notExpected, actual);
        //Teardown
    }

    @Test
    public void testFindOfficerById() {
        //Setup
        Officer expected = officer;
        //Exercise
        Officer actual = officerService.findById(officer.getId());
        //Assert
        assertEquals(expected.hashCode(), actual.hashCode());
        //Teardown
    }


    // UPDATE

    @Test
    public void testUpdateOfficerById() {
        //Setup
        officer.setFirst("New");
        officer.setLast("Officer");
        officer.setRank(Rank.ENSIGN);
        Officer expected = officer;
        //Exercise
        Officer actual = officerService.updateById(officer.getId(), officer);
        //Assert
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.getLast(), actual.getLast());
        //Teardown
    }


    // DELETE

    @Test
    public void testDeleteOfficerById() {
        //Exercise
        officerService.deleteById(officer.getId());
        Officer actual = officerService.findById(officer.getId());
        //Assert
        assertNull(actual);
        //Teardown
    }
}