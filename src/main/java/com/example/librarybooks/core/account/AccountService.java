package com.example.librarybooks.core.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo repo;

    public AccountService(AccountRepo repo) {
        this.repo = repo;
    }
}
