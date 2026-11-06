
package com.example.batchservice.dto;

public record BookRecord(
        Integer id,
        String title,
        String author,
        String isbn,
        String available
) {
}
