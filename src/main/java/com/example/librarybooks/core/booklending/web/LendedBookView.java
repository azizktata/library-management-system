package com.example.librarybooks.core.booklending.web;

import java.util.Date;

public class LendedBookView {
    private String bookId;

    private String bookTitle;

    private Long AccountId;

    private String AccountName;

    private Date lendedDate;

    private Date dueDate;

    private Date returnDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getAccountId() {
        return AccountId;
    }

    public void setAccountId(Long accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public Date getLendedDate() {
        return lendedDate;
    }

    public void setLendedDate(Date lendedDate) {
        this.lendedDate = lendedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
