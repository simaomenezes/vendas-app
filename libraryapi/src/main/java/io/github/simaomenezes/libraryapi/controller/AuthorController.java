package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.AuthorDTO;
import io.github.simaomenezes.libraryapi.controller.error.ErrorResponse;
import io.github.simaomenezes.libraryapi.controller.mappers.AuthorMapper;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
@Tag(name = "Authors")
@Slf4j
public class AuthorController implements GenericController {
    private final AuthorService service;
    private final AuthorMapper authorMapper;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Add", description = "Create new author")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created success."),
            @ApiResponse(responseCode = "422", description = "Validation Error."),
            @ApiResponse(responseCode = "409", description = "Author exist.")
    })
    public ResponseEntity<Object> add(@RequestBody @Valid AuthorDTO authorDTO){
        try {
            log.info("Created new Author: {}", authorDTO.name());
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
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    @Operation(summary = "Get Details", description = "Return data about author by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author founded."),
            @ApiResponse(responseCode = "404", description = "Author not found.")
    })
    public ResponseEntity<AuthorDTO> getDetail(@PathVariable("id") String id){
        var idAuthor = UUID.fromString(id);
        return service.findById(idAuthor).map(author -> {
            AuthorDTO authorDTO = authorMapper.toDTO(author);
            return ResponseEntity.ok(authorDTO);
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete", description = "Delete Author")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delete success."),
            @ApiResponse(responseCode = "404", description = "Author not found."),
            @ApiResponse(responseCode = "400", description = "The Author have a books created.")
    })
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        log.info("Delete author of ID: {} ", id);
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.findById(idAuthor);
        if (authorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.delete(authorOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    @Operation(summary = "Search", description = "Search Author by parameters")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success.")
    })
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "nationality", required = false) String nationality){
        List<Author> authors = service.search(name, nationality);
        List<AuthorDTO> authorDTOList = authors.stream().map(authorMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOList);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Update", description = "Update Author exist")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update Success."),
            @ApiResponse(responseCode = "404", description = "Author not found."),
            @ApiResponse(responseCode = "409", description = "Author as created")
    })
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
