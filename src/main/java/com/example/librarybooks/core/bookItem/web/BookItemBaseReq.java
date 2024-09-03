package com.example.librarybooks.core.bookItem.web;

import com.example.librarybooks.core.book.web.BookBaseReq;
import com.example.librarybooks.core.enums.BookFormat;
import org.springframework.lang.NonNull;
import javax.validation.constraints.NotEmpty;

public class BookItemBaseReq {

    @NonNull
    private String barcode;
    @NotEmpty
    private Long price;
    private String publicationDate;
    private Boolean isReferenceOnly;
    @NotEmpty
    private BookFormat format;

    private BookBaseReq bookInfo;

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
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

    public Boolean getReferenceOnly() {
        return isReferenceOnly;
    }

    public void setReferenceOnly(Boolean referenceOnly) {
        isReferenceOnly = referenceOnly;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public BookBaseReq getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookBaseReq bookInfo) {
        this.bookInfo = bookInfo;
    }
}
