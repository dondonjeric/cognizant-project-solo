package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.helper.Validator;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunitAdminAndManagerServiceImplTest {

    @Mock(name = "communityAdminAndManagerRepository")
    private CommunityAdminAndManagerRepository communityAdminAndManagerRepository;
    @Mock(name = "validator")
    private Validator validator;

    @InjectMocks
    private CommunitAdminAndManagerServiceImpl service;

    @Test
    @DisplayName("" +
            "Given the setup above " +
            "When createCommunityAdminAndManager(CommunityAdminAndManager.class) is executed " +
            "Then the result should return given")
    public void createCommunityAdminAndManager() throws InvalidInputException, JsonProcessingException {
        //ARRANGE
        CommunityAdminAndManager given
                = new CommunityAdminAndManager(1L,"Dondon", "dondonjeric.co@softvision.com", "2236562", "SamplePassword204", "Admin", true);
        when(communityAdminAndManagerRepository.save(any(CommunityAdminAndManager.class)))
                .thenReturn(given);
        //ACT
        CommunityAdminAndManager result = service.createCommunityAdminAndManager(given);

        //ASSERT
        assertEquals(given, result);

    }
}