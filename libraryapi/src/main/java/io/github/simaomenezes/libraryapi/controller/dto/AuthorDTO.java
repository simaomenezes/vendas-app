package io.github.simaomenezes.libraryapi.controller.dto;

import io.github.simaomenezes.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "Field required")
        @Size(min = 2, max = 100, message = "field with size character invalid")
        String name,
        @NotNull(message = "Field required")
        @Past(message = "Don't be future date")
        LocalDate dateBirthday,
        @NotBlank(message = "Field required")
        @Size(min = 2, max = 50, message = "field with size character invalid")
        String nationality
) {
}
