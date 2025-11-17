package com.example.cms.services;


import com.example.cms.models.Account;
import com.example.cms.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.getAllAccounts();
    }
}
