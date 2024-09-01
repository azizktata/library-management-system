package com.example.librarybooks.core.author;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo){
        this.authorRepo = authorRepo;
    }
}
