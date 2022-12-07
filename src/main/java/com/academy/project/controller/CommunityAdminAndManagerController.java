package com.academy.project.controller;

import com.academy.project.dto.CreateCommunityAdminAndManagerRequest;
import com.academy.project.exception.InvalidInputException;
import com.academy.project.handler.ErrorMessages;
import com.academy.project.handler.ResponseMessage;
import com.academy.project.mapper.Mapper;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.service.CommunitAdminAndManagerService;
import com.academy.project.service.CommunitAdminAndManagerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/community/manager")
public class CommunityAdminAndManagerController {

    @Autowired
    private CommunitAdminAndManagerServiceImpl service;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping()
    public ResponseEntity<ResponseMessage> createCommunityAdminAndManager(@RequestBody CreateCommunityAdminAndManagerRequest manager) throws InvalidInputException, JsonProcessingException {
        CommunityAdminAndManager create = modelMapper.map(manager, CommunityAdminAndManager.class);
        service.createCommunityAdminAndManager(create);
        return new ResponseEntity<>(new ResponseMessage(OK.value(), "Successfully created!", new ErrorMessages(null)),OK);
    }
}
