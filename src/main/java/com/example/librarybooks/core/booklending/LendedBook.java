package com.example.librarybooks.core.booklending;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.bookItem.BookItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LendedBook {

    @EmbeddedId
    private LendedBookId lendedBookId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookItem book;

    private Date lendedDate;

    private Date dueDate;

    private Date returnDate;

    public LendedBookId getLendedBookId() {
        return lendedBookId;
    }

    public void setLendedBookId(LendedBookId lendedBookId) {
        this.lendedBookId = lendedBookId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public BookItem getBook() {
        return book;
    }

    public void setBook(BookItem book) {
        this.book = book;
    }


}
