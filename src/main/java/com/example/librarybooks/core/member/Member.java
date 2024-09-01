package com.example.librarybooks.core.member;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.bookItem.BookItem;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("2")
public class Member extends Account {
    private Integer totalBooksCheckedout;
    @OneToMany
    private List<BookItem> borrowedBooks;
}
