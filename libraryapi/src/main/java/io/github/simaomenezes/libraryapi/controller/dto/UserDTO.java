package io.github.simaomenezes.libraryapi.controller.dto;

import java.util.List;

public record  UserDTO(String login, String password, String email, List<String> roles){}
