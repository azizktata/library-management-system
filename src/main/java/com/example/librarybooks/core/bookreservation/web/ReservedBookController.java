package com.example.librarybooks.core.bookreservation.web;

import com.example.librarybooks.core.bookreservation.ReservedBookId;
import com.example.librarybooks.core.bookreservation.ReservedBookService;
import com.example.librarybooks.core.enums.ReservationStatus;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserved-books")
public class ReservedBookController {
    private final ReservedBookService service;

    public ReservedBookController(ReservedBookService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservedBookView> getReservedBooks() {
        return service.findAllReservedBooks();
    }

    @GetMapping("/book/{bookId}/account/{accountId}")
    public ReservedBookView getReservedBook(@PathVariable String bookId, @PathVariable Long accountId) throws EntityNotFoundException {
        ReservedBookId reservedBookId = new ReservedBookId(accountId, bookId);
        return service.findReservedBook(reservedBookId);
    }

    @PostMapping
    public ReservedBookView createReservedBook(@RequestBody ReservedBookBaseReq req) throws EntityNotFoundException {
        return service.createReservedBook(req);
    }

    @PutMapping("/book/{bookId}/account/{accountId}")
    public ReservedBookView updateReserved (@PathVariable String bookId, @PathVariable Long accountId, @RequestParam("reservationStatus")ReservationStatus reservationStatus) throws EntityNotFoundException {
        ReservedBookId reservedBookId = new ReservedBookId(accountId, bookId);
        return service.updateReservationStatus(reservedBookId, reservationStatus);
    }

    @DeleteMapping("/book/{bookId}/account/{accountId}")
    public void deleteReservedBook(@PathVariable String bookId, @PathVariable Long accountId) throws EntityNotFoundException {
        ReservedBookId reservedBookId = new ReservedBookId(accountId, bookId);
        service.deleteReservedBook(reservedBookId);
    }
}
