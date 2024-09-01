package com.example.librarybooks.core.book.converter;

import com.example.librarybooks.core.author.converter.AuthorToAuthorView;
import com.example.librarybooks.core.author.web.AuthorView;
import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.book.web.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BookToBookView implements Converter<Book, BookView> {

    private final AuthorToAuthorView authorToAuthorViewConverter;

    public BookToBookView(AuthorToAuthorView authorToAuthorViewConverter) {
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
    }

    @Override
    public BookView convert(@NonNull Book book) {
        BookView view = new BookView();
        view.setISBN(book.getIsbn());
        view.setTitle(book.getTitle());
        view.setSubject(book.getSubject());
        view.setLanguage(book.getLanguage());
        List<AuthorView> authors = new ArrayList<>();
        book.getWrittenBy().forEach(author -> {
             authors.add(authorToAuthorViewConverter.convert(author));
        });
        view.setAuthors(authors);
        return view;
    }
}
