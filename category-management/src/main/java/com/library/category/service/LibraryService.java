package com.library.category.service;

import com.library.category.dto.SimplifiedLibraryDto;
import com.library.category.model.Library;
import com.library.category.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository, RestTemplate restTemplate) {
        this.libraryRepository = libraryRepository;
        this.restTemplate = restTemplate;
    }

    public Library saveLibrary(Library library) {

        Library savedLibrary = libraryRepository.save(library);


        SimplifiedLibraryDto simplifiedLibraryDto = new SimplifiedLibraryDto(
                savedLibrary.getId().toString(),
                savedLibrary.getName()
        );

        try {
            restTemplate.postForEntity(
                    "http://gateway:8080/api/simplified-libraries",
                    simplifiedLibraryDto,
                    SimplifiedLibraryDto.class
            );
        } catch (Exception e) {

            throw new RuntimeException("Failed to create SimplifiedLibrary in elements-management.", e);
        }

        return savedLibrary;
    }

    public Optional<Library> findLibraryById(UUID libraryId) {
        return libraryRepository.findById(libraryId);
    }

    public List<Library> findAllLibraries() {
        return libraryRepository.findAll();
    }

    public SimplifiedLibraryDto createSimplifiedLibrary(SimplifiedLibraryDto dto) {
        ResponseEntity<SimplifiedLibraryDto> response = restTemplate.postForEntity(
                "http://gateway:8080/api/simplified-libraries", dto, SimplifiedLibraryDto.class);
        return response.getBody();
    }

    public List<SimplifiedLibraryDto> getAllSimplifiedLibraries() {
        ResponseEntity<SimplifiedLibraryDto[]> response = restTemplate.getForEntity(
                "http://gateway:8080/api/simplified-libraries", SimplifiedLibraryDto[].class);
        return Arrays.asList(response.getBody());
    }

    public void deleteLibrary(UUID libraryId) {
        Optional<Library> library = libraryRepository.findById(libraryId);
        if (library.isPresent()) {
            restTemplate.delete("http://elements-management:8082/api/simplified-libraries/" + libraryId);

            libraryRepository.deleteById(libraryId);
        } else {
            throw new RuntimeException("Library not found with ID: " + libraryId);
        }
    }

    public Library updateLibrary(Library library) {
        Library updatedLibrary = libraryRepository.save(library);

        // Update SimplifiedLibrary in elements-management
        SimplifiedLibraryDto simplifiedLibraryDto = new SimplifiedLibraryDto(
                updatedLibrary.getId().toString(),
                updatedLibrary.getName()
        );

        try {
            restTemplate.put(
                    "http://gateway:8080/api/simplified-libraries/" + updatedLibrary.getId(),
                    simplifiedLibraryDto
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to update SimplifiedLibrary in elements-management.", e);
        }

        return updatedLibrary;
    }

}
