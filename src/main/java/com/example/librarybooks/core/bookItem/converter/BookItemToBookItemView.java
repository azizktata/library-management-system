package com.example.librarybooks.core.bookItem.converter;

import com.example.librarybooks.core.book.converter.BookToBookView;
import com.example.librarybooks.core.book.web.BookView;
import com.example.librarybooks.core.bookItem.BookItem;
import com.example.librarybooks.core.bookItem.web.BookItemView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookItemToBookItemView implements Converter <BookItem, BookItemView> {

    private final BookToBookView bookToBookView;

    public BookItemToBookItemView(BookToBookView bookToBookView) {
        this.bookToBookView = bookToBookView;
    }

    @Override
    public BookItemView convert(BookItem bookItem) {
        BookItemView bookItemView = new BookItemView();
        bookItemView.setBarcode(bookItem.getBarcode());
        bookItemView.setPrice(bookItem.getPrice());
        bookItemView.setPublicationDate(bookItem.getPublicationDate());
        bookItemView.setStatus(bookItem.getStatus());
        bookItemView.setReferenceOnly(bookItem.getReferenceOnly());
        bookItemView.setBorrowed(bookItem.getBorrowed());
        bookItemView.setFormat(bookItem.getFormat());
        BookView bookView = bookToBookView.convert(bookItem);
        bookItemView.setBookInfo(bookView);
        return bookItemView;
    }
}
