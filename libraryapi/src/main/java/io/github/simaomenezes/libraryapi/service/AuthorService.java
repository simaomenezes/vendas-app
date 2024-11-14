package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author add(Author author){
        return repository.save(author);
    }
}
