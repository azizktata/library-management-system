package com.example.librarybooks.core.account.web;

import com.example.librarybooks.core.account.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;


    public AccountController(AccountService service) {
        this.service = service;
    }


}
