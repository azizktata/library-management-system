package com.example.librarybooks.core.account;

import com.example.librarybooks.core.account.converter.AccountToAccountView;
import com.example.librarybooks.core.account.web.AccountBaseReq;
import com.example.librarybooks.core.account.web.AccountView;
import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.librarian.Librarian;
import com.example.librarybooks.core.member.Member;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepo repo;
    private final AccountToAccountView accountToAccountViewConverter;

    public AccountService(AccountRepo repo, AccountToAccountView accountToAccountViewConverter) {
        this.repo = repo;
        this.accountToAccountViewConverter = accountToAccountViewConverter;
    }
    public Account findAccountOrThrow(Long id) throws EntityNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such account found!"));
    }

    public AccountView getAccount(Long id) throws EntityNotFoundException {
        Account account = findAccountOrThrow(id);
        return accountToAccountViewConverter.convert(account);
    }

    public List<AccountView> findAllAccounts() {
        List<Account> accounts = repo.findAll();
        List<AccountView> accountViews = new ArrayList<>();
        accounts.forEach(account -> {
            accountViews.add(accountToAccountViewConverter.convert(account));
        });
        return accountViews;
    }
    public List<AccountView> findAllMembers() {
        List<Account> accounts = repo.findAll();
        List<AccountView> accountViews = new ArrayList<>();
        accounts.forEach(account -> {
            if (account instanceof Member){
            accountViews.add(accountToAccountViewConverter.convert(account));
            }
        });
        return accountViews;
    }
    public List<AccountView> findAllLibrarians() {
        List<Account> accounts = repo.findAll();
        List<AccountView> accountViews = new ArrayList<>();
        accounts.forEach(account -> {
            if (account instanceof Librarian){
            accountViews.add(accountToAccountViewConverter.convert(account));
            }
        });
        return accountViews;
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

    @Transactional
    public void deleteAccount(Long id) throws EntityNotFoundException {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("No such account found!");
        }
    }

    public AccountView update(Account account, AccountBaseReq req) {
        this.prepare(account,req);
        Account savedAccount = repo.save(account);
        return accountToAccountViewConverter.convert(savedAccount);
    }
}
