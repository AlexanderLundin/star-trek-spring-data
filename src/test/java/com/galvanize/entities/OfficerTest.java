package com.galvanize.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficerTest {
    //rlw test passed!

    @Test
    public void TestCreateEntity() {
        //Setup
        Officer officer  = new Officer();
        //Exercise
        officer.setId(1L);
        officer.setFirst("James");
        officer.setLast("Kirk");
        officer.setRank(Rank.CAPTAIN);
        //Assert
        assertEquals(1L, officer.getId());
        assertEquals("James", officer.getFirst());
        assertEquals("Kirk", officer.getLast());
        assertEquals(Rank.CAPTAIN, officer.getRank());
        //Teardown
    }

}