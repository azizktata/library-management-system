package com.example.librarybooks.core.bookItem;

import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.enums.BookFormat;
import com.example.librarybooks.core.enums.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class BookItem extends Book {

    @Column(unique = true)
    private String barcode;
    private Long price;
    private String publicationDate;
    private BookStatus status;
    private Boolean isReferenceOnly;
    private String Borrowed;
    private BookFormat format;

    public BookItem() {

    }


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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Boolean getReferenceOnly() {
        return isReferenceOnly;
    }

    public void setReferenceOnly(Boolean referenceOnly) {
        isReferenceOnly = referenceOnly;
    }

    public String getBorrowed() {
        return Borrowed;
    }

    public void setBorrowed(String borrowed) {
        Borrowed = borrowed;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }
}
