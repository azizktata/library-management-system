package com.example.librarybooks.core.book.web;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.web.AuthorView;
import com.example.librarybooks.core.enums.BookFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookView {

    private String ISBN;

    private String title;

    private String subject;

    private String language;

    private int numberOfPages;

    private List<AuthorView> authors = new ArrayList<>();

    public BookView() {}


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<AuthorView> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorView> authors) {
        this.authors = authors;
    }
}
