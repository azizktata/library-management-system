package com.example.librarybooks.core.bookItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepo extends JpaRepository<BookItem, Long> {
}
