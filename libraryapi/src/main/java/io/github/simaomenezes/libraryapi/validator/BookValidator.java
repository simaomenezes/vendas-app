package io.github.simaomenezes.libraryapi.validator;

import io.github.simaomenezes.libraryapi.exceptions.FieldInvalidException;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private static final int ANO_EXIGENCIA_PRECO = 2024;

    private final BookRepository repository;

    public void validator(Book book){
        if(existBookWithIsbn(book)){
            throw new RecordDuplicatedException("ISBN already.");
        }

        if(isPriceRequiredOrNull(book)){
            throw new FieldInvalidException("Price", "For books with year publiched");
        }
    }

    private boolean isPriceRequiredOrNull(Book book) {
        return book.getPrice() == null && book.getDatePublished().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existBookWithIsbn(Book book) {
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());
        if(book.getId() == null){
            return bookFound.isPresent();
        }
        return bookFound
                .map(Book::getId)
                .stream()
                .anyMatch(id -> !id.equals(book.getId()));
    }
}
