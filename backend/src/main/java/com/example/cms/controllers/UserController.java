package com.example.cms.controllers;

import com.example.cms.dto.UserDto;
import com.example.cms.models.User;
import com.example.cms.requests.UserEditRequest;
import com.example.cms.responses.BaseResponse;
import com.example.cms.security.CustomUserDetails;
import com.example.cms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse> getAllUsers() {

        List<User> users = userService.getAllUsers();
        BaseResponse baseResponse = new BaseResponse(200, users);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<BaseResponse> getLoggedInUser() {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDto user = userService.getUserById(userDetails.getId());
            BaseResponse baseResponse = new BaseResponse(200, user);
            return ResponseEntity.ok(baseResponse);

        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(400, e.getMessage());
            return ResponseEntity.ok(baseResponse);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<BaseResponse> editUser(@RequestBody UserEditRequest user) {
        try {
            userService.editUser(user);
            BaseResponse baseResponse = new BaseResponse(200, "Edited successfully");
            return ResponseEntity.ok(baseResponse);
        } catch (Exception e) {
            BaseResponse baseResponse = new BaseResponse(400, e.getMessage());
            return ResponseEntity.ok(baseResponse);
        }
    }

}
