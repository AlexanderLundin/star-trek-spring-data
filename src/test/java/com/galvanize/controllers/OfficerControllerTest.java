package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repository.JdbcOfficerDao;
import com.galvanize.repository.JpaOfficerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OfficerControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    JdbcTemplate jdbcTemplate;
    OfficerController controller;

    JpaOfficerDao jpaOfficerDao;

    @BeforeEach
    public void Setup() {
        //Setup
        jpaOfficerDao = new JpaOfficerDao();
        controller = new OfficerController(jdbcTemplate, jpaOfficerDao);
    }


    // CREATE


    @Test
    void TestPostOfficer() throws Exception {
        String body = "{\"id\":\"7\",\"rank\":\""+ Rank.ENSIGN +"\",\"first\":\"new\",\"last\":\"officer\"}";
        mvc.perform(post("/officers")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.first").value("new"));
    }


    //READ


    @Test
    public void TestGetAllOfficers() throws Exception {
        //Setup
        String url = "/officers";
        //Exercise and Assert
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kirk")));
        //Teardown
    }

    @Test
    public void TestGetOfficerByID() throws Exception {
        //Setup
        String url = "/officers/2";
        //Exercise and Assert
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Picard")));
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
    void TestPatchOfficerRank() throws Exception {
        //Setup
        String url = "/officers/1/"+ Rank.ENSIGN;
        //Exercise and Assert
        mvc.perform(patch(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Rank.ENSIGN.toString())));
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