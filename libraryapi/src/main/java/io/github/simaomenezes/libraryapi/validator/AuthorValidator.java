package io.github.simaomenezes.libraryapi.validator;

import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository){
        this.repository = repository;
    }

    public void validator(Author author){
        if(existAuthorSaved(author)){
            throw new RecordDuplicatedException("Author exist!");
        }
    }

    private boolean existAuthorSaved(Author author) {
        Optional<Author> authorFound = repository.findByNameAndDateBirthdayAndNationality(author.getName(), author.getDateBirthday(), author.getNationality());
        if(author.getId() != null){
            return authorFound.isPresent();
        }
        return !author.getId().equals(authorFound.get().getId()) && authorFound.isPresent();
    }
}
