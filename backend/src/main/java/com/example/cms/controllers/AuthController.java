package com.example.cms.controllers;


import com.example.cms.exceptions.ALreadyExistsException;
import com.example.cms.models.User;
import com.example.cms.responses.BaseResponse;
import com.example.cms.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<BaseResponse> signup(@RequestBody User user){
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm: "+user.getUsername());
        if(user.getEmail()==null || user.getPassword()==null || user.getUsername()==null){
            BaseResponse baseResponse = new BaseResponse(400, "Please fill the request fields");
            return ResponseEntity.status(400).body(baseResponse);
        }
        try{
            authService.signup(user);
        }
        catch (ALreadyExistsException e){
            return ResponseEntity.status(400).body(new BaseResponse(400, e.getMessage()));
        }
        return ResponseEntity.ok(new BaseResponse(200,"Success"));
    }

    @PostMapping("/signin")
    ResponseEntity<BaseResponse> signin(@RequestBody User user){
        if(user.getEmail()==null || user.getPassword()==null){
            BaseResponse baseResponse = new BaseResponse(400, "Please fill the request fields");
            return ResponseEntity.status(400).body(baseResponse);
        }
        try{
            String token = authService.signin(user);
            return ResponseEntity.ok(new BaseResponse(200, token));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new BaseResponse(400, e.getMessage()));
        }
    }

}
