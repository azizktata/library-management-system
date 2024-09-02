package com.example.librarybooks.core.account.web;

import com.example.librarybooks.core.account.AccountService;
import com.example.librarybooks.core.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;


    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountView addAccountM (@RequestBody @Valid AccountBaseReq req) {
        return service.createMember(req);
    }

    @PostMapping("/librarian")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountView addAccountL (@RequestBody @Valid AccountBaseReq req) {
        return service.createLibriarian(req);
    }
}
