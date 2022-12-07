package com.academy.project.helper;

import com.academy.project.exception.InvalidInputException;
import com.academy.project.model.CommunityAdminAndManager;
import com.academy.project.repository.CommunityAdminAndManagerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Configuration
@PropertySource("classpath:roletype.properties")
public class Validator {

    @Autowired
    private CommunityAdminAndManagerRepository repository;
    private static final String NAME = "[a-zA-Z]+[a-zA-Z- ,.Ññ]+";
    private static final String SPECIAL_CHARACTERS = "[- ,.Ññ]+[a-zA-Z- ,.Ññ]+";
    private static final String COGNIZANT_ID = "[0-9]+";
    private static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD =  "[a-zA-Z]+[a-zA-Z0-9]+";
    private static final String PASSWORD1 =  "[0-9]+[a-zA-Z]+[a-zA-Z0-9]+";

    public void checkCreateIfValid(CommunityAdminAndManager manager) throws JsonProcessingException, InvalidInputException {
        Map<String, List<String>> allMessages = checkIfValid(manager);
        if(allMessages.get("Name") != null || allMessages.get("CsvEmail") != null
                || allMessages.get("CognizantId") != null  || allMessages.get("Password") != null
                || allMessages.get("RoleType") != null ){
            String message = new ObjectMapper().writeValueAsString(allMessages);
            System.out.println("Invalid!");
            throw new InvalidInputException(message);
        }
    }

    public Map<String, List<String>> checkIfValid(CommunityAdminAndManager manager){
        Map<String, List<String>> allMessages = new LinkedHashMap<>();
        List<String> name = checkNameIfValid(manager.getName());
        if(name.size() > 0) allMessages.put("Name",name);
        List<String> email = checkEmailIfValid(manager.getCsvEmail());
        if(email.size() > 0) allMessages.put("CsvEmail",email);
        List<String> cognizantId = checkCognizantIdIfValid(manager.getCognizantId());
        if(cognizantId.size() > 0) allMessages.put("CognizantId",cognizantId);
        List<String> password = checkPasswordIfValid(manager.getPassword());
        if(password.size() > 0) allMessages.put("Password",password);
        List<String> roleType = checkRoleTypeIfValid(manager.getRoleType());
        if(roleType.size() > 0) allMessages.put("RoleType",roleType);
        return allMessages;
    }

    public List<String> checkNameIfValid(String name){
        List<String> messages = new ArrayList<>();
        if(name == null || name.isBlank()){
            messages.add("Name is required!");
        }
        if(name != null){
            if(name.length() > 100){
                messages.add("Name length must not exceed 100 characters!");
            }
            if(name.length() < 2){
                messages.add("Name length must exceed 2 characters!");
            }
            if(name.matches(SPECIAL_CHARACTERS) || !name.matches(NAME)){
                messages.add("Invalid name format!");
            }
        }
        return messages;
    }
    public List<String> checkCognizantIdIfValid(String cognizantId){
        List<String> messages = new ArrayList<>();
        if(cognizantId == null || cognizantId.isBlank()){
            messages.add("CognizantId is required!");
        }
        if(cognizantId != null){
            if(cognizantId.length() > 10){
                messages.add("CognizantId length must not exceed 10 characters!");
            }
            if(!cognizantId.matches(COGNIZANT_ID)){
                messages.add("Invalid CognizantId format!");
            }
            Optional<String> existing = repository.findByCognizantId(cognizantId);
            if(existing.isPresent()){
                messages.add("CognizantId must be unique!");
            }
        }
        return messages;
    }
    public List<String> checkEmailIfValid(String email){
        List<String> messages = new ArrayList<>();
        if(email == null || email.isBlank()){
            messages.add("Email is required!");
        }
        if(email != null){
            if(email.length() > 50){
                messages.add("Email length must not exceed 50 characters!");
            }
            if(!email.matches(EMAIL)){
                messages.add("Invalid email format!");
            }
            Optional<String> existing = repository.findByCsvEmail(email);
            if(existing.isPresent()){
                messages.add("Email must be unique!");
            }
        }
        return messages;
    }
    public List<String> checkPasswordIfValid(String password){
        List<String> messages = new ArrayList<>();
        if(password == null || password.isBlank()){
            messages.add("Password is required!");
        }
        if(password != null){
            if(password.length() > 100){
                messages.add("Password length must not exceed 100 characters!");
            }
            if(!password.matches(PASSWORD) && !password.matches(PASSWORD1)){
                messages.add("Invalid password format!");
            }
        }
        return messages;
    }

    @Value("${role.type.values}")
    private String[] roleTypes;
    public List<String> checkRoleTypeIfValid(String roleType){
        List<String> messages = new ArrayList<>();
        if(roleType == null || roleType.isBlank()){
            messages.add("RoleType is required!");
        }
        boolean match = false;
        for(String role: roleTypes){
            if(roleType.equals(role)){
                match = true;
                break;
            }
        }
        if(!match){
            messages.add("Invalid roletype!");
        }
        return messages;
    }
}
