package com.academy.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommunityAdminAndManagerRequest {

    private String name;
    private String csvEmail;
    private String cognizantId;
    private String password;
    private String roleType;
}
