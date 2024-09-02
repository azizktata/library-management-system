package com.example.librarybooks.core.account;

import com.example.librarybooks.core.account.converter.AccountToAccountView;
import com.example.librarybooks.core.account.web.AccountBaseReq;
import com.example.librarybooks.core.account.web.AccountView;
import com.example.librarybooks.core.librarian.Librarian;
import com.example.librarybooks.core.member.Member;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo repo;
    private final AccountToAccountView accountToAccountViewConverter;

    public AccountService(AccountRepo repo, AccountToAccountView accountToAccountViewConverter) {
        this.repo = repo;
        this.accountToAccountViewConverter = accountToAccountViewConverter;
    }

    public AccountView createMember(AccountBaseReq req) {
        Account account = new Member();
        this.prepare(account,req);
        Account savedAccount = repo.save(account);
        return accountToAccountViewConverter.convert(savedAccount);
    }
    public AccountView createLibriarian(AccountBaseReq req) {
        Account account = new Librarian();
        this.prepare(account,req);
        Account savedAccount = repo.save(account);
        return accountToAccountViewConverter.convert(savedAccount);
    }

    public Account prepare(Account acc, AccountBaseReq req) {
        acc.setName(req.getName());
        acc.setEmail(req.getEmail());
        acc.setPassword(req.getPassword());
        acc.setPhone(req.getPhone());
        return acc;
    }
}
