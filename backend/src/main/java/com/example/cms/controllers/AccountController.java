package com.example.cms.controllers;

import com.example.cms.models.Account;
import com.example.cms.responses.BaseResponse;
import com.example.cms.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse> getALlAccounts(){
        List<Account> accounts =  accountService.getAllAccounts();
        BaseResponse baseResponse = new BaseResponse(200, accounts);
        return ResponseEntity.ok(baseResponse);
    }

}
