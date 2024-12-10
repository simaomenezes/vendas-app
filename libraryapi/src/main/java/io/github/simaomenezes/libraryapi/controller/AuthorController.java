package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.AuthorDTO;
import io.github.simaomenezes.libraryapi.controller.error.ErrorResponse;
import io.github.simaomenezes.libraryapi.controller.mappers.AuthorMapper;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {
    private final AuthorService service;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid AuthorDTO authorDTO){
        try {
            Author author = authorMapper.toEntity(authorDTO);
            service.add(author);
            URI location = generatorHeaderLocation(author.getId());
            return ResponseEntity.created(location).build();
        } catch (RecordDuplicatedException e) {
            var errorDTO = ErrorResponse.responseConflict(e.getMessage());
            return ResponseEntity.status(errorDTO.status()).body(errorDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getDetail(@PathVariable("id") String id){
        var idAuthor = UUID.fromString(id);
        return service.findById(idAuthor).map(author -> {
            AuthorDTO authorDTO = authorMapper.toDTO(author);
            return ResponseEntity.ok(authorDTO);
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.findById(idAuthor);
        if (authorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(authorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality){
        List<Author> authors = service.search(name, nationality);
        List<AuthorDTO> authorDTOList = authors.stream().map(authorMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOList);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid AuthorDTO authorDTO){
        UUID idAuthor = UUID.fromString(id);
        Optional<Author> authorFound = service.findById(idAuthor);
        if(authorFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Author author = authorFound.get();
        author.setName(authorDTO.name());
        author.setNationality(authorDTO.nationality());
        author.setDateBirthday(authorDTO.dateBirthday());
        service.update(author);
        return ResponseEntity.noContent().build();
    }
}
