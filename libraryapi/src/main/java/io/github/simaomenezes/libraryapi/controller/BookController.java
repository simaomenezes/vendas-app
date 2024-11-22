package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.BookDTO;
import io.github.simaomenezes.libraryapi.controller.error.ErrorResponse;
import io.github.simaomenezes.libraryapi.controller.mappers.BookMapper;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GenericController{

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid BookDTO bookDTO){
        Book book = mapper.toEntity(bookDTO);
        service.add(book);
        var url = generatorHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }
}
