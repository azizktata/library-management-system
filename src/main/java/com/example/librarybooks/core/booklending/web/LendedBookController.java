package com.example.librarybooks.core.booklending.web;

import com.example.librarybooks.core.booklending.LendedBookId;
import com.example.librarybooks.core.booklending.LendedBookService;
import com.example.librarybooks.core.enums.BookStatus;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lended-books")
public class LendedBookController {
    private final LendedBookService service;

    public LendedBookController(LendedBookService service) {
        this.service = service;
    }

    @GetMapping
    public List<LendedBookView> getAllLendedBooks() {
        return service.getAllLendedBooks();
    }

    @GetMapping("/account/{accountId}")
    public List<LendedBookView> getLendedBooksByAccount(@PathVariable Long accountId) {
        return service.getAllLendedBooks();
    }

    @GetMapping("/account/{accountId}/book/{bookId}")
    public LendedBookView getLendedBookView(@PathVariable Long accountId,@PathVariable String bookId) throws EntityNotFoundException {
        LendedBookId lendedBookId = new LendedBookId(accountId, bookId);
        return service.getLendedBookView(lendedBookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LendedBookView create(@RequestBody LendedBookBaseReq req) throws EntityNotFoundException {
        return service.create(req);
    }

    @PutMapping("/account/{accountId}/book/{bookId}")
    public LendedBookView update(@PathVariable Long accountId,@PathVariable String bookId,@RequestBody LendedBookBaseReq req) throws EntityNotFoundException {
        LendedBookId lendedBookId = new LendedBookId(accountId, bookId);
        return service.update(lendedBookId, req);
    }

    @PutMapping("/reloan/account/{accountId}/book/{bookId}")
    public LendedBookView updateLoaning(@PathVariable Long accountId, @PathVariable String bookId, @RequestParam("newDueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date newDueDate ) throws EntityNotFoundException {
        LendedBookId lendedBookId = new LendedBookId(accountId, bookId);
        return service.renewLoaning(lendedBookId, newDueDate);
    }

    @DeleteMapping("/{lendedBookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLendedBook(@PathVariable LendedBookId lendedBookId) throws EntityNotFoundException {
        service.deleteLendedBook(lendedBookId);
    }
}
