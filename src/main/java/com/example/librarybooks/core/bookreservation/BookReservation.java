package com.example.librarybooks.core.bookreservation;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.bookItem.BookItem;
import com.example.librarybooks.core.enums.ReservationStatus;
import jakarta.persistence.*;

@Entity
public class BookReservation {

    @EmbeddedId
    private ReservedBookId reservedBookId;

    @ManyToOne
    @MapsId("accountId") // Maps the account_id in ReservedBookId to the Account entity's primary key
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private BookItem book;

    private String reservationDate;

    private ReservationStatus status;

    public BookReservation() {
    }

    public ReservedBookId getReservedBookId() {
        return reservedBookId;
    }

    public void setReservedBookId(ReservedBookId reservedBookId) {
        this.reservedBookId = reservedBookId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BookItem getBook() {
        return book;
    }

    public void setBook(BookItem book) {
        this.book = book;
    }
}
