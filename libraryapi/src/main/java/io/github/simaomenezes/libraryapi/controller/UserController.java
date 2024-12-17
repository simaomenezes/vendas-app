package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.UserDTO;
import io.github.simaomenezes.libraryapi.controller.mappers.UserMapper;
import io.github.simaomenezes.libraryapi.model.User;
import io.github.simaomenezes.libraryapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody UserDTO userDTO){
        var user = mapper.toEntity(userDTO);
        service.Add(user);
    }

}
