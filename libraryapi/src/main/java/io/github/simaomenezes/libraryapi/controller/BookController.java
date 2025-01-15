package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.controller.dto.BookDTO;
import io.github.simaomenezes.libraryapi.controller.dto.ResponseSearchBookDTO;
import io.github.simaomenezes.libraryapi.controller.error.ErrorResponse;
import io.github.simaomenezes.libraryapi.controller.mappers.BookMapper;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import io.github.simaomenezes.libraryapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController implements GenericController{

    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<Void> add(@RequestBody @Valid BookDTO bookDTO){
        Book book = mapper.toEntity(bookDTO);
        service.add(book);
        var url = generatorHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<ResponseSearchBookDTO> getDateail(@PathVariable("id") String id){
        return service.getById(UUID.fromString(id)).map(book -> {
            var dto = mapper.toDTO(book);
            return ResponseEntity.ok(dto);
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        return service.getById(UUID.fromString(id))
                .map(book-> {
                    service.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER') || hasAuthority('SCOPE_MANAGER')")
    public ResponseEntity<Page<ResponseSearchBookDTO>> search(
            @RequestParam(value = "isbn", required = false)
            String isbn,
            @RequestParam(value = "title", required = false)
            String title,
            @RequestParam(value = "nameAuthor", required = false)
            String nameAuthor,
            @RequestParam(value = "gender", required = false)
            BookGender gender,
            @RequestParam(value = "yearPublished", required = false)
            Integer yearPublished,
            @RequestParam(value = "page", defaultValue = "0")
            Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10")
            Integer pageSize
    ){
        Page<Book> resultPage = service.search(isbn, title, nameAuthor, gender, yearPublished, page, pageSize);
        //var list = result.stream().map(mapper::toDTO).collect(Collectors.toList());

        Page<ResponseSearchBookDTO> resulpageDTO = resultPage.map(mapper::toDTO);
        return ResponseEntity.ok(resulpageDTO);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<Object> update(
            @PathVariable("id") String id, @RequestBody @Valid BookDTO dto){
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    Book bookAux = mapper.toEntity(dto);
                    book.setDatePublished(bookAux.getDatePublished());
                    book.setIsbn(bookAux.getIsbn());
                    book.setPrice(bookAux.getPrice());
                    book.setGender(bookAux.getGender());
                    book.setTitle(bookAux.getTitle());
                    book.setAuthor(bookAux.getAuthor());
                    service.update(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build() );
    }
}
