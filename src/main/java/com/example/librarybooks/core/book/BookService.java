package com.example.librarybooks.core.book;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.web.AuthorBaseReq;
import com.example.librarybooks.core.author.web.AuthorView;
import com.example.librarybooks.core.book.converter.BookToBookView;
import com.example.librarybooks.core.book.web.BookBaseReq;
import com.example.librarybooks.core.book.web.BookView;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepo repo;
    private final BookToBookView bookToBookViewConverter;


    public BookService(BookRepo repo, BookToBookView bookToBookViewConverter) {
        this.repo = repo;
        this.bookToBookViewConverter = bookToBookViewConverter;
    }

    public Book findBookOrThrow(String id) throws EntityNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such book found!"));
    }

    public BookView getBook (String id) throws EntityNotFoundException {
        Book book = findBookOrThrow(id);
        return bookToBookViewConverter.convert(book);
    }

    public Page<BookView> findAllBooks(Pageable pageable) {
        Page<Book> books = repo.findAll(pageable);
        List<BookView> bookViews = new ArrayList<>();
        books.forEach(book -> {
            BookView bookView = bookToBookViewConverter.convert(book);
            bookViews.add(bookView);
        });
        return new PageImpl<>(bookViews, pageable, books.getTotalElements());
    }

    @Transactional
    public void delete(String id) throws EntityNotFoundException {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("author not found");
        }
    }

    public BookView create (BookBaseReq req) {
        Book book = new Book();
        this.prepareBook(book, req);
        Book savedBook = repo.save(book);
        return bookToBookViewConverter.convert(book);
    }

    public BookView update (Book book, BookBaseReq req) {
        this.prepareBook(book, req);
        Book savedBook = repo.save(book);
        return bookToBookViewConverter.convert(book);
    }

    public Author prepareAuthor(Author author, AuthorBaseReq req) {
        author.setName(req.getName());
        author.setDescription(req.getDescription());
        return author;
    }
    public Book prepareBook(Book book, BookBaseReq req) {
        book.setIsbn(req.getIsbn());
        book.setSubject(req.getSubject());
        book.setLanguage(req.getLanguage());
        book.setTitle(req.getTitle());
        book.setNumberOfPages(req.getNumberOfPages());
        List<Author> authors = new ArrayList<>();
        req.getAuthorReqs().forEach(authorReq -> {
            Author author = new Author();
            authors.add(this.prepareAuthor(author, authorReq));
        });
        book.setWrittenBy(authors);

        return book;
    }
}
