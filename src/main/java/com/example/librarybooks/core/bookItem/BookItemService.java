package com.example.librarybooks.core.bookItem;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.web.AuthorBaseReq;
import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.book.web.BookBaseReq;
import com.example.librarybooks.core.book.web.BookView;
import com.example.librarybooks.core.bookItem.converter.BookItemToBookItemView;
import com.example.librarybooks.core.bookItem.web.BookItemBaseReq;
import com.example.librarybooks.core.bookItem.web.BookItemView;
import com.example.librarybooks.core.enums.BookStatus;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookItemService {

    private final BookItemRepo repo;
    private final BookItemToBookItemView bookItemToBookItemViewConverter;

    public BookItemService(BookItemRepo repo, BookItemToBookItemView bookItemToBookItemViewConverter) {
        this.repo = repo;
        this.bookItemToBookItemViewConverter = bookItemToBookItemViewConverter;
    }

    public BookItem findBookItemOrThrow(String barcode) throws EntityNotFoundException {
        return repo.findByBarcode(barcode)
                .orElseThrow(() -> new EntityNotFoundException("No such book item found!"));
    }

    public BookItemView getBookItem(String barcode) throws EntityNotFoundException {
        BookItem bookItem = findBookItemOrThrow(barcode);
        return bookItemToBookItemViewConverter.convert(bookItem);
    }

    public BookItemView getBookItemByISBN(String isbn) throws EntityNotFoundException {
        BookItem bookItem = repo.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("No such book item found!"));
        return bookItemToBookItemViewConverter.convert(bookItem);
    }

    public List<BookItemView> findAllBookItems() {
        List<BookItem> bookItems = repo.findAll();
        List<BookItemView> bookItemViews = new ArrayList<>();
        bookItems.forEach(bookItem -> {
            bookItemViews.add(bookItemToBookItemViewConverter.convert(bookItem));
        });

        return bookItemViews;
    }

   // public Page<BookView> findAllBooks(Pageable pageable) {
     //   Page<Book> books = repo.findAll(pageable);
       // List<BookView> bookViews = new ArrayList<>();
        //books.forEach(book -> {
          //  BookView bookView = bookToBookViewConverter.convert(book);
            //bookViews.add(bookView);
        //});
        //return new PageImpl<>(bookViews, pageable, books.getTotalElements());
    //}


    public List<BookItemView> findAllAvailableBookItems() {
        List<BookItem> bookItems = repo.findAll();
        List<BookItemView> bookItemViews = new ArrayList<>();
        bookItems.forEach(bookItem -> {
            if (isAvailaible(bookItem)) {
                bookItemViews.add(bookItemToBookItemViewConverter.convert(bookItem));
            }
        });
        return bookItemViews;
    }

    private boolean isAvailaible(BookItem bookItem) {
        return bookItem.getStatus().equals(BookStatus.Available) && !bookItem.getReferenceOnly();
    }



    public List<BookItemView> findBooksByTitle(String title) {
        List<BookItem> books = repo.findByTitle(title);
        List<BookItemView> bookViews = new ArrayList<>();
        books.forEach(book -> {
            BookItemView bookItemView = bookItemToBookItemViewConverter.convert(book);
            bookViews.add(bookItemView);
        });
        return bookViews;
    }

    public Page<BookItemView> findBooksBySubject(String subject, Pageable pageable) {
        Page<BookItem> books = repo.findBySubject(subject, pageable);
        List<BookItemView> bookViews = new ArrayList<>();
        books.forEach(book -> {
            BookItemView bookItemView = bookItemToBookItemViewConverter.convert(book);
            bookViews.add(bookItemView);
        });
        return new PageImpl<>(bookViews, pageable, books.getTotalElements());
    }


    public List<BookItemView> findBooksByAuthorName(String authorName) {
        List<BookItem> books = repo.findBooksByAuthorName(authorName);
        List<BookItemView> bookViews = new ArrayList<>();
        books.forEach(book -> {
            BookItemView bookItemView = bookItemToBookItemViewConverter.convert(book);
            bookViews.add(bookItemView);
        });
        return bookViews;
    }

    public void delete(String barcode) throws EntityNotFoundException {
        BookItem bookItem = findBookItemOrThrow(barcode);
        repo.delete(bookItem);
    }

    public BookItemView create (BookItemBaseReq req) {
        BookItem bookItem = new BookItem();
        this.prepare(bookItem, req);
        bookItem.setStatus(BookStatus.Available);
        bookItem = repo.save(bookItem);
        return bookItemToBookItemViewConverter.convert(bookItem);

    }

    public BookItemView update (BookItem bookItem, BookItemBaseReq req) {
        this.prepare(bookItem, req);
        bookItem = repo.save(bookItem);
        return bookItemToBookItemViewConverter.convert(bookItem);
    }
    public Author prepareAuthor(Author author, AuthorBaseReq req) {
        author.setName(req.getName());
        author.setDescription(req.getDescription());
        return author;
    }

    public BookItem prepare(BookItem bookItem, BookItemBaseReq req) {
        bookItem.setBarcode(req.getBarcode());
        bookItem.setPrice(req.getPrice());
        bookItem.setPublicationDate(req.getPublicationDate());
        bookItem.setFormat(req.getFormat());
        bookItem.setReferenceOnly(req.getReferenceOnly());

        BookBaseReq bookBaseReq = req.getBookInfo();
        bookItem.setIsbn(bookBaseReq.getIsbn());
        bookItem.setTitle(bookBaseReq.getTitle());
        bookItem.setSubject(bookBaseReq.getSubject());
        bookItem.setLanguage(bookBaseReq.getLanguage());
        bookItem.setNumberOfPages(bookBaseReq.getNumberOfPages());

        List<Author> authors = new ArrayList<>();
        req.getBookInfo().getAuthorReqs().forEach(authorReq -> {
            Author author = new Author();
            authors.add(this.prepareAuthor(author, authorReq));
        });
        bookItem.setWrittenBy(authors);

        return bookItem;
    }
}
