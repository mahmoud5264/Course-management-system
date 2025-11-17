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
public class Account {
    private UUID id;
    private String user_name;
    private String email;
    private String password ;
    private String type;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String is_verified;
}
