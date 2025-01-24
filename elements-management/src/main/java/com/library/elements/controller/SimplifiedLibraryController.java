package com.library.elements.controller;

import com.library.elements.dto.BookReadDto;
import com.library.elements.dto.SimplifiedLibraryDto;
import com.library.elements.model.Book;
import com.library.elements.model.SimplifiedLibrary;
import com.library.elements.service.SimplifiedLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.elements.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/simplified-libraries")
public class SimplifiedLibraryController {

    private final SimplifiedLibraryService libraryService;

    @Autowired
    public SimplifiedLibraryController(SimplifiedLibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<List<SimplifiedLibraryDto>> getAllLibraries() {
        List<SimplifiedLibraryDto> libraries = libraryService.findAllLibraries().stream()
                .map(library -> new SimplifiedLibraryDto(library.getId().toString(), library.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(libraries);
    }

    @PostMapping
    public ResponseEntity<SimplifiedLibraryDto> createSimplifiedLibrary(@RequestBody SimplifiedLibraryDto libraryDto) {
        SimplifiedLibrary library = SimplifiedLibrary.builder()
                .id(UUID.fromString(libraryDto.getId()))
                .name(libraryDto.getName())
                .build();
        SimplifiedLibrary savedLibrary = libraryService.saveLibrary(library);

        return ResponseEntity.ok(new SimplifiedLibraryDto(
                savedLibrary.getId().toString(),
                savedLibrary.getName()
        ));
    }

    @DeleteMapping("/{libraryId}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable UUID libraryId) {
        libraryService.deleteLibraryAndBooks(libraryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{libraryId}")
    public ResponseEntity<SimplifiedLibraryDto> updateSimplifiedLibrary(
            @PathVariable UUID libraryId,
            @RequestBody SimplifiedLibraryDto libraryDto) {
        Optional<SimplifiedLibrary> optionalLibrary = libraryService.findLibraryById(libraryId);

        if (optionalLibrary.isPresent()) {
            SimplifiedLibrary existingLibrary = optionalLibrary.get();

            // Update fields
            existingLibrary.setName(libraryDto.getName());

            SimplifiedLibrary updatedLibrary = libraryService.saveLibrary(existingLibrary);

            return ResponseEntity.ok(new SimplifiedLibraryDto(
                    updatedLibrary.getId().toString(),
                    updatedLibrary.getName()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
