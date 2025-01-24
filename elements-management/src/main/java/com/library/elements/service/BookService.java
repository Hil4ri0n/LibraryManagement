package com.library.elements.service;

import com.library.elements.model.Book;
import com.library.elements.model.SimplifiedLibrary;
import com.library.elements.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(UUID id) {
        return bookRepository.findById(id);
    }

    public List<Book> findBooksByLibrary(SimplifiedLibrary library) {
        return bookRepository.findByLibrary(library);
    }

    public List<Book> findBooksByLibraryId(UUID libraryId) {
        return bookRepository.findByLibraryId(libraryId);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
