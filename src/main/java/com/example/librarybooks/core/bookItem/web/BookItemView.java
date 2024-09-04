package com.example.librarybooks.core.bookItem.web;

import com.example.librarybooks.core.book.web.BookView;
import com.example.librarybooks.core.enums.BookFormat;
import com.example.librarybooks.core.enums.BookStatus;

import java.util.Date;

public class BookItemView {

    private String barcode;
    private Long price;
    private String publicationDate;
    private BookStatus status;
    private Boolean isReferenceOnly;
    private Date Borrowed;
    private BookFormat format;
    private BookView bookInfo;

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

    public Date getBorrowed() {
        return Borrowed;
    }

    public void setBorrowed(Date borrowed) {
        Borrowed = borrowed;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }


    public BookView getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookView bookInfo) {
        this.bookInfo = bookInfo;
    }
}
