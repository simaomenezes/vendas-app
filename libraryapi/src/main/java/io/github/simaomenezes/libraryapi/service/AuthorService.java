package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.exceptions.OperationNotAllowException;
import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.model.User;
import io.github.simaomenezes.libraryapi.repository.AuthorRepository;
import io.github.simaomenezes.libraryapi.repository.BookRepository;
import io.github.simaomenezes.libraryapi.security.SecurityService;
import io.github.simaomenezes.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;
    private final SecurityService securityService;


    public Author add(Author author){
        User user = securityService.getUserLogin();
        author.setUser(user);
        validator.validator(author);
        return repository.save(author);
    }

    public void update(Author author){
        if(author.getId() == null){
            throw new IllegalArgumentException("The to save, the author need on saved the bd data");
        }
        validator.validator(author);
        repository.saveAndFlush(author);
    }

    public Optional<Author> findById(UUID id){
        return repository.findById(id);
    }

    public void delete(Author author){
        if(hashBook(author)){
            throw new OperationNotAllowException("Can't delete author with a book created!");
        }
        repository.delete(author);
    }

    private boolean hashBook(Author author) {
        return bookRepository.existsByAuthor(author);
    }

    public List<Author> search(String name, String nationality){
        if(name != null && nationality != null){
            return repository.findByNameAndNationality(name, nationality);
        }

        if(name != null){
            return repository.findByName(name);
        }

        if(nationality != null){
            return  repository.findByNationality(nationality);
        }
        return repository.findAll();
    }
}