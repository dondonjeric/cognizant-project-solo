package com.academy.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "communityadminandmanager")
public class CommunityAdminAndManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communityadminandmanagerid")
    private Long id;
    @Column(name = "communityadminandmanagername")
    private String name;
    @Column(name = "csvemail")
    private String csvEmail;
    @Column(name = "cognizantid")
    private String cognizantId;
    @Column(name = "password")
    private String password;
    @Column(name = "roletype")
    private String roleType;
    @Column(name = "isactive")
    private Boolean isActive = true;

}
