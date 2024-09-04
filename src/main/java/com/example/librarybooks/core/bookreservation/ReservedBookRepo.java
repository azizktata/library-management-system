package com.example.librarybooks.core.bookreservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedBookRepo extends JpaRepository<ReservedBook, ReservedBookId> {
}
