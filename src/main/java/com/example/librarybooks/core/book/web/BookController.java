package com.example.librarybooks.core.book.web;


import com.example.librarybooks.core.book.Book;
import com.example.librarybooks.core.book.BookService;
import com.example.librarybooks.core.bookItem.BookItemService;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public BookView getBook(@PathVariable String id) throws EntityNotFoundException {
        return service.getBook(id);
    }

    @GetMapping
    public Page<BookView> getAllBook(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return service.findAllBooks(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookView create(@RequestBody @Valid BookBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String id) throws EntityNotFoundException {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public BookView updateBook(@PathVariable(name = "id") String isbn,
                                   @RequestBody @Valid BookBaseReq req) throws EntityNotFoundException {
        Book book = service.findBookOrThrow(isbn);
        return service.update(book, req);
    }
}
