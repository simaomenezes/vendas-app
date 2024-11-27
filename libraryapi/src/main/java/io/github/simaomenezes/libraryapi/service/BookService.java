package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import io.github.simaomenezes.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.simaomenezes.libraryapi.repository.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book add(Book book){
        return repository.save(book);
    }

    public Optional<Book> getById(UUID id){
        return repository.findById(id);
    }

    public void delete(Book book){
        repository.delete(book);
    }

    public List<Book> search(
            String isbn,
            String title,
            String nameAuthor,
            BookGender gender,
            Integer yearPublished){
        // select * from livro where 0 = 0
        Specification<Book> specs = Specification.where((root, query, cb) -> cb.conjunction() );

        if(isbn != null){
            // query = query and isbn = :isbn
            specs = specs.and(isbnEqual(isbn));
        }

        if(title != null){
            specs = specs.and(titleLike(title));
        }

        if(gender != null){
            specs = specs.and(genderEqual(gender));
        }

        if(yearPublished != null){
            specs = specs.and(yearPublishedEqual(yearPublished));
        }

        if(nameAuthor != null){
            specs = specs.and(nameAuthorLike(nameAuthor));
        }
        return repository.findAll(specs);
    }

    public void update(Book book){
        if(book.getId() == null){
            throw new IllegalArgumentException("For the update, the book is need on saved on BD");
        }
        repository.save(book);
    }
}
