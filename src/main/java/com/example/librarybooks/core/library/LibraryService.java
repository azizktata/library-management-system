package com.example.librarybooks.core.library;

import com.example.librarybooks.core.library.web.LibraryBaseReq;
import com.example.librarybooks.error.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepo libraryRepo;

    public LibraryService(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    public List<Library> getAllLibraries() {
        return libraryRepo.findAll();
    }

    public Library getLibrarieByName(String name) {
        return libraryRepo.findByName(name);
    }

    public Library findOrThrow(Long id) throws EntityNotFoundException {
        return libraryRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("library.NotFound"));
    }

    public Library createLibrary(LibraryBaseReq req) {
        Library library = new Library();
        library.setName(req.getName());
        library.setAddress(req.getAddress());

        return libraryRepo.save(library);
    }

    public Library updateLibrary(LibraryBaseReq req, Long id) throws EntityNotFoundException {
        Library library = findOrThrow(id);
        library.setName(req.getName());
        library.setAddress(req.getAddress());

        return libraryRepo.save(library);
    }

    public void deleteLibrary(Long id) throws EntityNotFoundException {
        Library library = findOrThrow(id);
        libraryRepo.delete(library);
    }
}
