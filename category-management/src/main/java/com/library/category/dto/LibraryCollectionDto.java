package com.library.category.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryCollectionDto {
    private String id;
    private String name;

    public LibraryCollectionDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
