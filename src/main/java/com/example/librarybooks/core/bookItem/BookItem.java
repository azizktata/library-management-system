package com.example.librarybooks.core.bookItem;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.enums.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class BookItem extends Book {

    @Id
    @Column(name = "barcode", nullable = false)
    private String barcode;
    private Long price;
    private Date publicationDate;
    private BookStatus status;

    @ManyToOne
    private Account borrower;


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
