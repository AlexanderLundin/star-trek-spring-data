package com.galvanize.controllers;

import com.galvanize.services.OfficerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("default")
class HomeControllerTest {


    @Autowired
    MockMvc mvc;


    @Autowired
    OfficerService officerService;

    @Test
    public void testHomeController() throws Exception {
        String url = "/home";
        mvc.perform(get(url))
            .andExpect(status().isOk());
    }
}