package com.example.librarybooks.core.booklending;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LendedBookRepo extends JpaRepository<LendedBook, LendedBookId> {
    @Query ("SELECT lb FROM LendedBook lb WHERE lb.lendedBookId.accountId = :accountId")
    Optional<List<LendedBook>> findByAccountId(Long accountId);
}
