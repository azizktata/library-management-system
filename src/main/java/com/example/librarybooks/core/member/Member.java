package com.example.librarybooks.core.member;

import com.example.librarybooks.core.account.Account;
import jakarta.persistence.Entity;

@Entity
public class Member extends Account {
    private Integer totalBooksCheckedout;

    public Member() {
    }
//    @OneToMany
//    private List<BookItem> borrowedBooks;
}
