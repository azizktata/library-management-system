package com.example.librarybooks.core.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepo extends JpaRepository<Library, Long> {
    Library findByName(String name);
}
