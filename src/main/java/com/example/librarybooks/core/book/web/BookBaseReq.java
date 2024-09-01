package com.example.librarybooks.core.book.web;

import com.example.librarybooks.core.author.web.AuthorBaseReq;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BookBaseReq {

    @Id
    @NotEmpty
    private String isbn;

    @NotEmpty
    private String title;

    private String subject;

    @NotEmpty
    private String language;

    private int numberOfPages;

    @NotEmpty
    private List<AuthorBaseReq> authorReqs;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public List<AuthorBaseReq> getAuthorReqs() {
        return authorReqs;
    }

    public void setAuthorReqs(List<AuthorBaseReq> authorReqs) {
        this.authorReqs = authorReqs;
    }
}
