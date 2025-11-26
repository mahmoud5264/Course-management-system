package com.example.cms.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String type;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String isVerified;
}
