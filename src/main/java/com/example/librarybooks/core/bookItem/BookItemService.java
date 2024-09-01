package com.example.librarybooks.core.bookItem;

import org.springframework.stereotype.Service;

@Service
public class BookItemService {

    private final BookItemRepo repo;

    public BookItemService(BookItemRepo repo) {
        this.repo = repo;
    }
}
