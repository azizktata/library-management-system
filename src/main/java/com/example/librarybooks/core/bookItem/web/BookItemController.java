package com.example.librarybooks.core.bookItem.web;

import com.example.librarybooks.core.book.web.BookView;
import com.example.librarybooks.core.bookItem.BookItem;
import com.example.librarybooks.core.bookItem.BookItemService;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookItems")
public class BookItemController {
    private final BookItemService service;

    public BookItemController(BookItemService service) {
        this.service = service;
    }

//    @GetMapping
//    public Page<BookView> getAllBook(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
//        return service.findAllBooks(pageable);
//    }

    @GetMapping
    public List<BookItemView> getAllBookItems() {
        return service.findAllBookItems();
    }

    @GetMapping("/available")
    public List<BookItemView> getAllAvailableBookItems() {
        return service.findAllAvailableBookItems();
    }

    @GetMapping("/book/{isbn}")
    public List<BookItemView> getAllBookItemsOfBook(@PathVariable String isbn) {
        return service.findAllBookItemsOfBook(isbn);
    }

    @GetMapping("/{barcode}")
    public BookItemView getBookItem(@PathVariable String barcode) throws EntityNotFoundException {
        return service.getBookItem(barcode);
    }

    @GetMapping("/search/title/{title}")
    public List<BookItemView> searchBookByTitle(@PathVariable String title){
        return service.findBooksByTitle(title);
    }

    @GetMapping("/search/author/{authorName}")
    public List<BookItemView> searchBookByAuthorName(@PathVariable String authorName){
        return service.findBooksByAuthorName(authorName);
    }

    @GetMapping("/search/subject/{subject}")
    public Page<BookItemView> searchBookBySubject(@PathVariable String subject, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return service.findBooksBySubject(subject, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookItemView createBookItem(@RequestBody BookItemBaseReq req) {
        return service.create(req);
    }

    @PutMapping("/{barcode}")
    public BookItemView updateBookItem(@PathVariable String barcode, @RequestBody BookItemBaseReq req) throws EntityNotFoundException {
        BookItem bookItem = service.findBookItemOrThrow(barcode);
        return service.update(bookItem, req);
    }

    @DeleteMapping("/{barcode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookItem(@PathVariable String barcode) throws EntityNotFoundException {
        service.delete(barcode);
    }
}
