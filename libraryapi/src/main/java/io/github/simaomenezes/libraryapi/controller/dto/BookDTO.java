package io.github.simaomenezes.libraryapi.controller.dto;

import io.github.simaomenezes.libraryapi.model.BookGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookDTO(
        @ISBN
        @NotBlank(message = "field required")
        String isbn,
        @NotBlank(message = "field required")
        String title,
        @NotNull(message = "field required")
        @Past(message = "can't be date on future")
        LocalDate datePublished,
        BookGender gender,
        BigDecimal price,
        @NotNull(message = "field required")
        UUID idAuthor
) {
}
