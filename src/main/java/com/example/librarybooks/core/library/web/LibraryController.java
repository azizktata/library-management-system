package com.example.librarybooks.core.library.web;

import com.example.librarybooks.core.library.Library;
import com.example.librarybooks.core.library.LibraryService;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Libraries")
public class LibraryController {
    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Library> getLibraries() {
        return service.getAllLibraries();
    }

    @GetMapping("/{libraryId}")
    public Library getLibrary(@PathVariable Long libraryId) throws EntityNotFoundException {
        return service.findOrThrow(libraryId);
    }

    @PostMapping
    public Library createLibrary(@RequestBody LibraryBaseReq req) {
        return service.createLibrary(req);
    }

    @PutMapping("/{libraryId}")
    public Library updateLibrary(@PathVariable Long libraryId, @RequestBody LibraryBaseReq req) throws EntityNotFoundException {

        return service.updateLibrary(req, libraryId);
    }

    @DeleteMapping("/{libraryId}")
    public void deleteLibrary(@PathVariable Long libraryId) throws EntityNotFoundException {
        service.deleteLibrary(libraryId);
    }
}
