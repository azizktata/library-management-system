package com.example.librarybooks.core.member;

import com.example.librarybooks.core.account.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Member extends Account {
    private Integer totalBooksCheckedout;
}
