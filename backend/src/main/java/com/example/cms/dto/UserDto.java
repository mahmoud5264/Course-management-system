package com.example.cms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String type;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
}
