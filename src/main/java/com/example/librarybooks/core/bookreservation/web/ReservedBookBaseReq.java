package com.example.librarybooks.core.bookreservation.web;

public class ReservedBookBaseReq {
    private String bookId;

    private Long accountId;

    public ReservedBookBaseReq() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
