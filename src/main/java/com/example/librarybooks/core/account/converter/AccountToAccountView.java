package com.example.librarybooks.core.account.converter;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.account.web.AccountView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountView implements Converter<Account, AccountView> {

    @Override
    public AccountView convert(Account acc) {
        AccountView view = new AccountView();
        view.setId(acc.getId());
        view.setName(acc.getName());
        view.setEmail(acc.getEmail());
        view.setPassword(acc.getPassword());
        view.setPhone(acc.getPhone());
        view.setDtype(acc.getDiscriminatorValue());
        return view;
    }
}
