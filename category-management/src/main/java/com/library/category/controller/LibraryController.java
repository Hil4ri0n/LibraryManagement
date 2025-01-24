package com.library.category.controller;

import com.library.category.dto.*;
import com.library.category.model.Library;
import com.library.category.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<LibraryReadDto> createLibrary(@RequestBody LibraryCreateDto libraryCreateDto) {
        Library library = Library.builder()
                .id(UUID.randomUUID())
                .name(libraryCreateDto.getName())
                .location(libraryCreateDto.getLocation())
                .build();
        Library savedLibrary = libraryService.saveLibrary(library);
        return ResponseEntity.ok(new LibraryReadDto(
                savedLibrary.getId().toString(),
                savedLibrary.getName(),
                savedLibrary.getLocation()
        ));
    }

    @GetMapping
    public ResponseEntity<List<LibraryCollectionDto>> getAllLibraries() {
        List<LibraryCollectionDto> libraries = libraryService.findAllLibraries().stream()
                .map(library -> new LibraryCollectionDto(
                        library.getId().toString(),
                        library.getName()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(libraries);
    }

    @GetMapping("/{libraryId}")
    public ResponseEntity<LibraryReadDto> getLibrary(@PathVariable UUID libraryId) {
        Optional<Library> library = libraryService.findLibraryById(libraryId);
        return library.map(value -> ResponseEntity.ok(new LibraryReadDto(
                        value.getId().toString(),
                        value.getName(),
                        value.getLocation()
                )))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{libraryId}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable UUID libraryId) {
        try {
            libraryService.deleteLibrary(libraryId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{libraryId}")
    public ResponseEntity<LibraryReadDto> updateLibrary(
            @PathVariable UUID libraryId,
            @RequestBody LibraryCreateDto libraryUpdateDto) {
        Optional<Library> optionalLibrary = libraryService.findLibraryById(libraryId);

        if (optionalLibrary.isPresent()) {
            Library existingLibrary = optionalLibrary.get();

            // Update fields
            existingLibrary.setName(libraryUpdateDto.getName());
            existingLibrary.setLocation(libraryUpdateDto.getLocation());

            // Save updated library
            Library updatedLibrary = libraryService.updateLibrary(existingLibrary);

            return ResponseEntity.ok(new LibraryReadDto(
                    updatedLibrary.getId().toString(),
                    updatedLibrary.getName(),
                    updatedLibrary.getLocation()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
