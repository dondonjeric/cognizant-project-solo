package com.academy.project.controller;

import com.academy.project.dto.CreateCommunityAdminAndManagerRequest;
import com.academy.project.mapper.Mapper;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunitAdminAndManagerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class CommunityAdminAndManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private CommunitAdminAndManagerServiceImpl service;

    @Test
    @DisplayName("" +
            "Given " +
            "When " +
            "Then ")
    public void createCommunityAdminAndManager_HappyPath() throws Exception {
        //ARRANGE
        CreateCommunityAdminAndManagerRequest request
                = new CreateCommunityAdminAndManagerRequest("Dondon", "dondonjeric.co@softvision.com", "2236562", "SamplePassword204", "Admin");
        CommunityAdminAndManager response
                = new CommunityAdminAndManager(1L,"Dondon", "dondonjeric.co@softvision.com", "2236562", "SamplePassword204", "Admin", true);
        //ACT
        when(service.createCommunityAdminAndManager(any(CommunityAdminAndManager.class)))
                .thenReturn(response);

        mockMvc.perform(post("/community/manager")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();
    }
}