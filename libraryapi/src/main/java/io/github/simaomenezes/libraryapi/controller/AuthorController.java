package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.AuthorDTO;
import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AuthorDTO authorDTO){
        Author author = authorDTO.mapperToAuthor();
        service.add(author);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
