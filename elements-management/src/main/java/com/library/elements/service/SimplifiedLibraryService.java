package com.library.elements.service;

import com.library.elements.model.SimplifiedLibrary;
import com.library.elements.repository.BookRepository;
import com.library.elements.repository.SimplifiedLibraryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedLibraryService {

    private final BookRepository bookRepository;
    private final SimplifiedLibraryRepository libraryRepository;

    @Autowired
    public SimplifiedLibraryService(BookRepository bookRepository, SimplifiedLibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    public List<SimplifiedLibrary> findAllLibraries() {
        return libraryRepository.findAll();
    }

    public Optional<SimplifiedLibrary> findLibraryById(UUID id) {
        return libraryRepository.findById(id);
    }

    public SimplifiedLibrary saveLibrary(SimplifiedLibrary library) {
        return libraryRepository.save(library);
    }

    @Transactional
    public void deleteLibraryAndBooks(UUID libraryId) {
        bookRepository.deleteAllByLibraryId(libraryId);

        libraryRepository.deleteById(libraryId);
    }

}
