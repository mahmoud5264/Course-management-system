package com.example.cms.requests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UserEditRequest {
    private UUID id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
}
