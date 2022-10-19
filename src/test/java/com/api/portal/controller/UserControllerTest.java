package com.api.portal.controller;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

   // @SpyBean
    //ProductService prodService;


    @Autowired
    private MockMvc mvc;

    @WithMockUser(username = "alvaro")
    @Test
    void getUsersListReturns200() throws Exception {

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/users-management/users"));

        result.andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    void getUsersListReturns401() throws Exception {

        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.get("/users-management/users"));

        result.andExpect(status().is4xxClientError());

    }
}
