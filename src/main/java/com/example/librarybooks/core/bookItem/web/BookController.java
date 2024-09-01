package com.example.librarybooks.core.bookItem.web;

import com.example.librarybooks.core.bookItem.BookItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookItemService service;

    public BookController (BookItemService service) {
        this.service = service;
    }
}
