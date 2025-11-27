package com.example.cms.requests;


import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String email;
    private String password;
    private String username;
}
