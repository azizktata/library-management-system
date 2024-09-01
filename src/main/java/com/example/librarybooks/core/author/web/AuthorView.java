package com.example.librarybooks.core.author.web;

import com.example.librarybooks.core.book.web.BookView;

import java.util.List;

public class AuthorView {

    private long id;

    private String name;

    private String description;

    private List<BookView> writtenBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookView> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<BookView> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }
}
