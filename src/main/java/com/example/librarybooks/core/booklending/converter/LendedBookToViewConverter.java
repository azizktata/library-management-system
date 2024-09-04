package com.example.librarybooks.core.booklending.converter;

import com.example.librarybooks.core.booklending.LendedBook;
import com.example.librarybooks.core.booklending.web.LendedBookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LendedBookToViewConverter implements Converter<LendedBook, LendedBookView> {


    @Override
    public LendedBookView convert(LendedBook LendedBook) {
        LendedBookView lendedBookView = new LendedBookView();
        lendedBookView.setBookId(LendedBook.getLendedBookId().getBookId());
        lendedBookView.setBookTitle(LendedBook.getBook().getTitle());
        lendedBookView.setAccountId(LendedBook.getLendedBookId().getAccountId());
        lendedBookView.setAccountName(LendedBook.getAccount().getName());
        lendedBookView.setLendedDate(LendedBook.getLendedDate());
        lendedBookView.setDueDate(LendedBook.getDueDate());
        lendedBookView.setReturnDate(LendedBook.getReturnDate());

        return lendedBookView;
    }
}
