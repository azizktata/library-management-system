package com.example.librarybooks.core.author;

import com.example.librarybooks.core.author.converter.AuthorToAuthorView;
import com.example.librarybooks.core.author.web.AuthorBaseReq;
import com.example.librarybooks.core.author.web.AuthorView;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorToAuthorView authorToAuthorView;

    public AuthorService(AuthorRepo authorRepo, AuthorToAuthorView authorToAuthorView){
        this.authorRepo = authorRepo;
        this.authorToAuthorView = authorToAuthorView;
    }

    public Author findAuthorOrThrow(String id) throws EntityNotFoundException {
        return authorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such author found!"));
    }

    public AuthorView getAuthor(String id) throws EntityNotFoundException {
        Author author = findAuthorOrThrow(id);
        return authorToAuthorView.convert(author);
    }

    public AuthorView create(AuthorBaseReq req) {
        Author author = new Author();
        this.prepare(author, req);
        author.setName(author.getName().toLowerCase());
        Author authorSave = authorRepo.save(author);
        return authorToAuthorView.convert(authorSave);
    }

    public Page<AuthorView> findAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepo.findAll(pageable);
        List<AuthorView> authorViews = new ArrayList<>();
        authors.forEach(author -> {
            AuthorView authorView = authorToAuthorView.convert(author);
            authorViews.add(authorView);
        });
        return new PageImpl<>(authorViews, pageable, authors.getTotalElements());
    }

    @Transactional
    public void delete(String id) throws EntityNotFoundException {
        try {
            authorRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("author not found");
        }
    }

    public AuthorView update(Author author, AuthorBaseReq req) {
        Author newAuthor = this.prepare(author, req);
        Author savedAuthor = authorRepo.save(newAuthor);
        return authorToAuthorView.convert(savedAuthor);
    }

    public Author prepare(Author author, AuthorBaseReq req) {
        author.setName(req.getName());
        author.setDescription(req.getDescription());
        return author;
    }
}
