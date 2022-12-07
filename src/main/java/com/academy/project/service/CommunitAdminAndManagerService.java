package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CommunitAdminAndManagerService {

    CommunityAdminAndManager createCommunityAdminAndManager(CommunityAdminAndManager create) throws InvalidInputException, JsonProcessingException;
    CommunityAdminAndManager updateCommunityAdminAndManager(CommunityAdminAndManager update);
}
