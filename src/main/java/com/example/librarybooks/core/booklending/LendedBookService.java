package com.example.librarybooks.core.booklending;

import com.example.librarybooks.core.account.Account;
import com.example.librarybooks.core.account.AccountRepo;
import com.example.librarybooks.core.bookItem.BookItem;
import com.example.librarybooks.core.bookItem.BookItemRepo;
import com.example.librarybooks.core.booklending.converter.LendedBookToViewConverter;
import com.example.librarybooks.core.booklending.web.LendedBookBaseReq;
import com.example.librarybooks.core.booklending.web.LendedBookView;
import com.example.librarybooks.core.enums.BookStatus;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LendedBookService {
    private final LendedBookRepo lendedBookRepo;
    private final AccountRepo accountRepo;
    private final BookItemRepo bookItemRepo;
    private final LendedBookToViewConverter lendedBookToViewConverter;

    public LendedBookService(LendedBookRepo lendedBookRepo, AccountRepo accountRepo, BookItemRepo bookItemRepo, LendedBookToViewConverter lendedBookToViewConverter) {
        this.lendedBookRepo = lendedBookRepo;
        this.accountRepo = accountRepo;
        this.bookItemRepo = bookItemRepo;
        this.lendedBookToViewConverter = lendedBookToViewConverter;
    }


    public LendedBook findLendedBookOrThrow(LendedBookId lendedBookId) throws EntityNotFoundException {
        return lendedBookRepo.findById(lendedBookId).orElseThrow(() -> new EntityNotFoundException("Lended book not found"));
    }

    public LendedBookView getLendedBookView(LendedBookId lendedBookId) throws EntityNotFoundException {
        LendedBook lendedBook = findLendedBookOrThrow(lendedBookId);
        return lendedBookToViewConverter.convert(lendedBook);
    }

    public List<LendedBookView> getAllLendedBooks() {
        List<LendedBook> lendedBooks = lendedBookRepo.findAll();
        List<LendedBookView> lendedBookViews = new ArrayList<>();
        lendedBooks.forEach(lendedBook -> {
            lendedBookViews.add(lendedBookToViewConverter.convert(lendedBook));
        });
        return lendedBookViews;
    }

    public List<LendedBookView> getLendedBooksByAccount(Long accountId) throws EntityNotFoundException {
        List<LendedBook> lendedBooks = lendedBookRepo.findByAccountId(accountId).orElseThrow( () -> new EntityNotFoundException("No Lended Books by this user"));
        List<LendedBookView> lendedBookViews = new ArrayList<>();
        lendedBooks.forEach(lendedBook -> {
            lendedBookViews.add(lendedBookToViewConverter.convert(lendedBook));
        });
        return lendedBookViews;
    }

    public void deleteLendedBook(LendedBookId lendedBookId) throws EntityNotFoundException {
        LendedBook lendedBook = findLendedBookOrThrow(lendedBookId);
        lendedBookRepo.delete(lendedBook);
    }

    public LendedBookView create(LendedBookBaseReq req) throws EntityNotFoundException {
        LendedBook lendedBook = new LendedBook();
        this.prepare(req, lendedBook);
        Account account = accountRepo.findById(req.getAccountId()).orElseThrow(() -> new EntityNotFoundException("Account not found"));
        BookItem bookItem = bookItemRepo.findById(req.getBookId()).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        if (bookItem.getReferenceOnly() || (bookItem.getStatus() == BookStatus.Lost || bookItem.getStatus() == BookStatus.Loaned)) {
            throw new EntityNotFoundException("You can't borrow this book");
        } else {
            if (req.getNumberOfBooks() > 5) {
                throw new EntityNotFoundException("You can't borrow more than 5 books");
            } else {
                if (bookItem.getStatus() == BookStatus.Reserved)  {
                    throw new EntityNotFoundException("Book is already reserved");
                }
                else {
                    bookItem.setBorrowed(new Date());
                    bookItem.setStatus(BookStatus.Loaned);
                    lendedBook.setAccount(account);
                    lendedBook.setBook(bookItem);
                    lendedBook.setLendedDate(new Date());
                    lendedBook = lendedBookRepo.save(lendedBook);
                }
            }
        }

        return lendedBookToViewConverter.convert(lendedBook);
    }

    public LendedBookView update(LendedBookId lendedBookId, LendedBookBaseReq req) throws EntityNotFoundException {
        LendedBook lendedBook = findLendedBookOrThrow(lendedBookId);
        this.prepare(req, lendedBook);
        LendedBook lendedBookSave = lendedBookRepo.save(lendedBook);
        return lendedBookToViewConverter.convert(lendedBookSave);
    }

    public LendedBookView renewLoaning(LendedBookId lendedBookId, Date newDueDate) throws EntityNotFoundException {
        LendedBook lendedBook = findLendedBookOrThrow(lendedBookId);
        Date currentDate = new Date();


        BookItem bookItem = lendedBook.getBook();
        if (bookItem.getStatus() != BookStatus.Loaned) {
            throw new EntityNotFoundException("Cannot renew loaning for a book that is not checked-out");
        }
        if (currentDate.before(lendedBook.getReturnDate())) {
            throw new EntityNotFoundException("The book must be returned before renewing loaning");
        }
        lendedBook.setDueDate(newDueDate);

        LendedBook lendedBookSave = lendedBookRepo.save(lendedBook);
        return lendedBookToViewConverter.convert(lendedBookSave);
    }

    private LendedBook prepare(LendedBookBaseReq req, LendedBook lendedBook) {
        LendedBookId lendedBookId = new LendedBookId(req.getAccountId(), req.getBookId());
        lendedBook.setLendedBookId(lendedBookId);
        lendedBook.setReturnDate(req.getReturnDate());
        lendedBook.setDueDate(req.getDueDate());
        return lendedBook;
    }
}
