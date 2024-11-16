package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author add(Author author){
        return repository.save(author);
    }

    public void update(Author author){
        if(author.getId() == null){
            throw new IllegalArgumentException("The to save, the author need on saved the bd data");
        }
        repository.saveAndFlush(author);
    }

    public Optional<Author> findById(UUID id){
        return repository.findById(id);
    }

    public void delete(Author author){
        repository.delete(author);
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