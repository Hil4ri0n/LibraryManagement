package com.library.elements.repository;

import com.library.elements.model.Book;
import com.library.elements.model.SimplifiedLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByLibrary(SimplifiedLibrary library);

    List<Book> findByLibraryId(UUID libraryId);

    void deleteAllByLibraryId(UUID libraryId);
}
