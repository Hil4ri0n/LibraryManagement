package com.library.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryReadDto {
    private String id;
    private String name;
    private String location;

}
