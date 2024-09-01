package com.example.librarybooks.core.library;

import jakarta.persistence.*;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String Address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
