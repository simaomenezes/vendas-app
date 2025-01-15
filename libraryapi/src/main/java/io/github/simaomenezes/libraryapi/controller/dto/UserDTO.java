package io.github.simaomenezes.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record  UserDTO(
        @NotBlank(message = "field required")
        String login,
        @NotBlank(message = "field required")
        String password,
        @Email(message = "not valid")
        @NotBlank(message = "field required")
        String email,
        List<String> roles){}
