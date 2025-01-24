package com.library.elements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookReadDto {
    private String id;
    private String title;
    private String author;
}
