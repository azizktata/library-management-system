package com.example.librarybooks.core.author.converter;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.web.AuthorView;

import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.book.converter.BookToBookView;
import com.example.librarybooks.core.book.web.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class AuthorToAuthorView implements Converter<Author, AuthorView> {

    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        view.setName(author.getName());
        view.setDescription(author.getDescription());

        return view;

    }

}
