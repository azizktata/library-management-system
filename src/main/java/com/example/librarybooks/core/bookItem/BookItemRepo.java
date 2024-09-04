package com.example.librarybooks.core.bookItem;

import com.example.librarybooks.core.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookItemRepo extends JpaRepository<BookItem, String> {
    Optional<BookItem> findByBarcode(String barcode);
    List<BookItem> findByTitle(String title);
    Page<BookItem> findBySubject(String subject, Pageable pageable);

    //Page<Book> findByWrittenBy_Name(String authorName, Pageable pageable);
    @Query("SELECT b FROM BookItem b JOIN b.writtenBy a WHERE a.name = :authorName")
    List<BookItem> findBooksByAuthorName(String authorName);

    Optional<BookItem> findByIsbn(String isbn);
}
