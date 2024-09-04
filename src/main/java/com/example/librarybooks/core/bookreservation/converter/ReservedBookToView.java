package com.example.librarybooks.core.bookreservation.converter;

import com.example.librarybooks.core.bookreservation.ReservedBook;
import com.example.librarybooks.core.bookreservation.web.ReservedBookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservedBookToView implements Converter<ReservedBook, ReservedBookView> {
    @Override
    public ReservedBookView convert(ReservedBook reservedBook) {
        ReservedBookView view = new ReservedBookView();
        view.setBookId(reservedBook.getBook().getIsbn());
        view.setBookTitle(reservedBook.getBook().getTitle());
        view.setAccountId(reservedBook.getAccount().getAccountId());
        view.setAccountName(reservedBook.getAccount().getName());
        view.setReservationDate(reservedBook.getReservationDate());
        view.setStatus(reservedBook.getStatus());
        return view;
    }
}
