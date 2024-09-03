package com.example.librarybooks.core.book;

import com.example.librarybooks.core.author.Author;
import jakarta.persistence.*;

import java.util.List;

@MappedSuperclass
public  class Book {
    @Id
    @Column(name = "isbn", nullable = false)
    private String isbn;

    private String title;

    private String subject;

    private String language;

    private int numberOfPages;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "book_author",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_author") })
    private List<Author> writtenBy;

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public List<Author> getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(List<Author> writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
