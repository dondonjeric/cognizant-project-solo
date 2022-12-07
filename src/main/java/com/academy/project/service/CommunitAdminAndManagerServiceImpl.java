package com.academy.project.service;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.helper.Validator;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunitAdminAndManagerServiceImpl implements CommunitAdminAndManagerService {

    @Autowired
    private CommunityAdminAndManagerRepository communityAdminAndManagerRepository;

    @Autowired
    private Validator validator;

    @Override
    public CommunityAdminAndManager createCommunityAdminAndManager(CommunityAdminAndManager create) throws InvalidInputException, JsonProcessingException {
        validator.checkCreateIfValid(create);
        return communityAdminAndManagerRepository.save(create);
    }

    @Override
    public CommunityAdminAndManager updateCommunityAdminAndManager(CommunityAdminAndManager update) {
        return null;
    }
}
