package com.library.elements.repository;

import com.library.elements.model.SimplifiedLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedLibraryRepository extends JpaRepository<SimplifiedLibrary, UUID> {
}
