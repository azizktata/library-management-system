package com.example.librarybooks.core.author.web;

import com.example.librarybooks.core.author.Author;
import com.example.librarybooks.core.author.AuthorService;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public AuthorView getAuthor(@PathVariable Long id) throws EntityNotFoundException {
        return service.getAuthor(id);
    }

    @GetMapping
    public Page<AuthorView> getAllAuthor(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return service.findAllAuthors(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorView create(@RequestBody @Valid AuthorBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) throws EntityNotFoundException {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AuthorView updateAuthor(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid AuthorBaseReq req) throws EntityNotFoundException {
        Author author = service.findAuthorOrThrow(id);
        return service.update(author, req);
    }

}
