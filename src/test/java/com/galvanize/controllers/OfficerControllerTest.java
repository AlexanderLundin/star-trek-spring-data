package com.galvanize.controllers;
// rlw - unused imports (code/Optimize Imports)

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repositories.JpaOfficerDao;
import com.galvanize.services.OfficerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
// references from application-test.properties
@ActiveProfiles("test")
class OfficerControllerTest {


    @Autowired
    MockMvc mvc;


    @Autowired
    OfficerService officerService;


    @Autowired
    ObjectMapper objectMapper;


    private Officer officer1;
    private String first = "James II";
    private String last = "Kirk";
    private Rank rank = Rank.COMMANDER;
    private long id;

    @BeforeEach
    public void setup() {
        //Setup
        officer1 = new Officer(rank, first, last);
        officer1 = officerService.save(officer1);
        id = officer1.getId();
    }


    // CREATE


    @Test
    void TestPostOfficer() throws Exception {
        String jsonInString = objectMapper.writeValueAsString(officer1);
        ResultActions resultActions = mvc.perform(post("/officers")
                .content(jsonInString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Officer officer = objectMapper.readValue(contentAsString, Officer.class);
        //Assert
        assertEquals(Rank.COMMANDER, officer.getRank());
        //Teardown
    }


    //READ


    @Test
    public void TestGetAllOfficers() throws Exception {
        //Setup
        String url = "/officers";
        //Exercise and Assert
        ResultActions resultActions = mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        List<Officer> cars = Arrays.asList(objectMapper.readValue(contentAsString, Officer[].class));
        int actual = cars.size();
        int unexpected = 0;
        //Assert
        assertNotEquals(unexpected, actual);
        //Teardown
    }

    @Test
    public void TestGetOfficerByID() throws Exception {
        //Setup
        String url = "/officers/" + id;
        //Exercise and Assert
        ResultActions resultActions = mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Officer officer = objectMapper.readValue(contentAsString, Officer.class);
        //Assert
        assertNotNull(officer);
        //Teardown
    }

    @Test
    public void TestGetOfficerByIDInvalid() throws Exception {
        //Setup
        String url = "/officers/0";
        //Exercise and Assert
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk());
        //Teardown
    }


    //UPDATE


    @Test
    void TestPatchOfficer() throws Exception {
        //Setup
        String jsonInString = objectMapper.writeValueAsString(officer1);
        String url = "/officers/" + id;
        //Exercise and Assert
        ResultActions resultActions = mvc.perform(patch(url)
                .content(jsonInString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Officer officer = objectMapper.readValue(contentAsString, Officer.class);
        //Assert
        assertEquals(Rank.COMMANDER, officer.getRank());
        //Teardown
    }


    //DELETE


    @Test
    public void TestDeleteOfficerByID() throws Exception {
        //Setup
        String url = "/officers/1/";
        //Exercise and Assert
        mvc.perform(delete(url))
                .andDo(print())
                .andExpect(status().isOk());
        //Teardown
    }
}