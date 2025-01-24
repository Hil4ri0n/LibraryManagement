package com.library.elements.controller;

import com.library.elements.dto.*;
import com.library.elements.model.Book;
import com.library.elements.model.SimplifiedLibrary;
import com.library.elements.service.BookService;
import com.library.elements.service.SimplifiedLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final SimplifiedLibraryService libraryService;

    @Autowired
    public BookController(BookService bookService, SimplifiedLibraryService libraryService) {
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<BookCollectionDto>> getAllBooks() {
        List<BookCollectionDto> books = bookService.findAllBooks().stream()
                .map(book -> new BookCollectionDto(book.getId().toString(), book.getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @PostMapping("/{libraryId}")
    public ResponseEntity<BookReadDto> addBookToLibrary(@PathVariable UUID libraryId,
                                                        @RequestBody BookCreateDto bookCreateDto) {
        Optional<SimplifiedLibrary> libraryOptional = libraryService.findLibraryById(libraryId);
        if (libraryOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SimplifiedLibrary library = libraryOptional.get();
        Book book = Book.builder()
                .id(UUID.randomUUID())
                .title(bookCreateDto.getTitle())
                .author(bookCreateDto.getAuthor())
                .library(library)
                .build();
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(new BookReadDto(savedBook.getId().toString(), savedBook.getTitle(), savedBook.getAuthor()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookReadDto> getBookById(@PathVariable UUID bookId) {
        Optional<Book> bookOptional = bookService.findBookById(bookId);
        return bookOptional.map(book -> ResponseEntity.ok(new BookReadDto(
                book.getId().toString(),
                book.getTitle(),
                book.getAuthor()
        ))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<BookReadDto>> getBooksByLibraryId(@PathVariable UUID libraryId) {
        List<Book> books = bookService.findBooksByLibraryId(libraryId);
        List<BookReadDto> bookDtos = books.stream()
                .map(book -> new BookReadDto(book.getId().toString(), book.getTitle(), book.getAuthor()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDtos);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID bookId) {
        if (bookService.findBookById(bookId).isPresent()) {
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookReadDto> updateBook(
            @PathVariable UUID bookId,
            @RequestBody BookCreateDto bookCreateDto) {
        Optional<Book> optionalBook = bookService.findBookById(bookId);

        if (optionalBook.isPresent()) {
            Book updatedBook = optionalBook.get();
            updatedBook.setTitle(bookCreateDto.getTitle());
            updatedBook.setAuthor(bookCreateDto.getAuthor());

            Book savedBook = bookService.saveBook(updatedBook);

            return ResponseEntity.ok(new BookReadDto(
                    savedBook.getId().toString(),
                    savedBook.getTitle(),
                    savedBook.getAuthor()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
