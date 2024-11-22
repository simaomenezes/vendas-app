package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book add(Book book){
        return repository.save(book);
    }
}
