package com.example.librarybooks.core.bookreservation;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.account.AccountRepo;
import com.example.librarybooks.core.bookItem.BookItem;
import com.example.librarybooks.core.bookItem.BookItemRepo;
import com.example.librarybooks.core.bookreservation.converter.ReservedBookToView;
import com.example.librarybooks.core.bookreservation.web.ReservedBookBaseReq;
import com.example.librarybooks.core.bookreservation.web.ReservedBookView;
import com.example.librarybooks.core.enums.BookStatus;
import com.example.librarybooks.core.enums.ReservationStatus;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservedBookService {
    private final ReservedBookRepo repo;
    private final BookItemRepo bookItemRepo;
    private final AccountRepo accountRepo;
    private final ReservedBookToView reservedBookToViewConverter;

    public ReservedBookService(ReservedBookRepo repo, BookItemRepo bookItemRepo, AccountRepo accountRepo, ReservedBookToView reservedBookToViewConverter) {
        this.repo = repo;
        this.bookItemRepo = bookItemRepo;
        this.accountRepo = accountRepo;
        this.reservedBookToViewConverter = reservedBookToViewConverter;
    }

    public ReservedBook findReservedBookOrThrow(ReservedBookId id) throws EntityNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Such reservation found with id: " + id));
    }

    public ReservedBookView findReservedBook(ReservedBookId id) throws EntityNotFoundException {
        ReservedBook reservedBook = findReservedBookOrThrow(id);
        return reservedBookToViewConverter.convert(reservedBook);
    }

    public List<ReservedBookView> findAllReservedBooks() {
        List<ReservedBook> reservedBooks = repo.findAll();
        return reservedBooks.stream().map(reservedBookToViewConverter::convert).toList();
    }

    public void deleteReservedBook(ReservedBookId id) throws EntityNotFoundException {
        ReservedBook reservedBook = findReservedBookOrThrow(id);
        repo.deleteById(id);
    }

    public ReservedBookView createReservedBook(ReservedBookBaseReq req) throws EntityNotFoundException {

        BookItem bookItem = bookItemRepo.findByIsbn(req.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("No such book found with isbn: " + req.getBookId()));
        Account account = accountRepo.findById(req.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("No such account found with id: " + req.getAccountId()));

        if (bookItem.getStatus().equals(BookStatus.Reserved)) {
            throw new EntityNotFoundException("Book is already reserved");
        }
        if (bookItem.getStatus().equals(BookStatus.Available)) {
            throw new EntityNotFoundException("You can only reserve a book that is borrowed");
        }
        ReservedBook reservedBook = new ReservedBook();
        ReservedBookId reservedBookId = new ReservedBookId(req.getAccountId(), req.getBookId());
        reservedBook.setReservedBookId(reservedBookId);
        reservedBook.setReservationDate(new Date());
        bookItem.setStatus(BookStatus.Reserved);
        reservedBook.setBook(bookItem);
        reservedBook.setAccount(account);
        reservedBook.setStatus(ReservationStatus.Pending);

        ReservedBook reservedBookSave = repo.save(reservedBook);
        return reservedBookToViewConverter.convert(reservedBookSave);
    }

    public ReservedBookView updateReservationStatus(ReservedBookId id, ReservationStatus status) throws EntityNotFoundException {
        ReservedBook reservedBook = findReservedBookOrThrow(id);
        reservedBook.setStatus(status);
        ReservedBook reservedBookSave = repo.save(reservedBook);
        return reservedBookToViewConverter.convert(reservedBookSave);
    }
}
