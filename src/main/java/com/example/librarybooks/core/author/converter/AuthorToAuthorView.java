package com.example.librarybooks.core.author.converter;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.web.AuthorView;

import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.book.converter.BookToBookView;
import com.example.librarybooks.core.book.web.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorToAuthorView implements Converter<Author, AuthorView> {

    private final BookToBookView bookToBookViewConverter;

    public AuthorToAuthorView(BookToBookView bookToBookViewConverter) {
        this.bookToBookViewConverter = bookToBookViewConverter;
    }

    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setId(author.getId());
        view.setName(author.getName());
        view.setDescription(author.getDescription());
        List<BookView> books = new ArrayList<>();
        for(Book b : author.getWrittenBooks())
            books.add(bookToBookViewConverter.convert(b));
        view.setWrittenBooks(books);
        return view;

    }

}
