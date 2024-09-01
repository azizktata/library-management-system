package com.example.librarybooks.core.librarian;

import com.example.librarybooks.core.account.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Librarian extends Account {
}
