package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.repository.JdbcOfficerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OfficerControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    JdbcTemplate jdbcTemplate;
    OfficerController controller;

    @BeforeEach
    public void Setup() {
        //Setup
        //controller = new OfficerController(jdbcTemplate);
    }

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

}