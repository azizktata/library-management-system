package com.example.librarybooks.core.bookreservation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReservedBookId implements Serializable {

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "book_id")
    private String bookId;

    public ReservedBookId(Long accountId, String bookId) {
        this.accountId = accountId;
        this.bookId = bookId;
    }

    public ReservedBookId() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
