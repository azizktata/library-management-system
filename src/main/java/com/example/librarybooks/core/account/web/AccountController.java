package com.example.librarybooks.core.account.web;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.account.AccountService;
import com.example.librarybooks.core.member.Member;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;


    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<AccountView> getAllAccounts() {
        return service.findAllAccounts();
    }

    @GetMapping("/member")
    public List<AccountView> getAllMembers() {
        return service.findAllMembers();
    }

    @GetMapping("/librarian")
    public List<AccountView> getAllLibrarians() {
        return service.findAllLibrarians();
    }

    @GetMapping("/{id}")
    public AccountView getAccount(@PathVariable Long id) throws EntityNotFoundException {
        return service.getAccount(id);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) throws EntityNotFoundException {
        service.deleteAccount(id);
    }

    @PutMapping("/{id}")
    public AccountView updateAccount(@PathVariable(name = "id") Long id,
                                     @RequestBody @Valid AccountBaseReq req) throws EntityNotFoundException {
        Account account = service.findAccountOrThrow(id);
        return service.update(account, req);
    }
}
