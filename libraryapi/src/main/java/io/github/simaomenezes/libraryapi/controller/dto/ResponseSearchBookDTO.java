package io.github.simaomenezes.libraryapi.controller.dto;

import io.github.simaomenezes.libraryapi.model.BookGender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResponseSearchBookDTO(
        UUID id,
        String isbn,
        String title,
        LocalDate datePublished,
        BookGender gender,
        BigDecimal price,
        AuthorDTO authorDTO
) {
}
