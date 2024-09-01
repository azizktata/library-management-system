package com.example.librarybooks.core.bookItem.web;

import com.example.librarybooks.core.bookItem.BookItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookItems")
public class BookItemController {
    private final BookItemService service;

    public BookItemController(BookItemService service) {
        this.service = service;
    }
}
