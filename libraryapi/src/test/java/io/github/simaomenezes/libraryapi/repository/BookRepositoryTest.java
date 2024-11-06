package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;
    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void addTest(){
        Book book = new Book();
        book.setIsbn("98522-55889");
        book.setGender(BookGender.FANTASY);
        book.setTitle("My Title");
        book.setDatePublished(LocalDate.of(1855, 1, 3));

        var author = authorRepository.findById(UUID.fromString("e0e6463b-74bd-472e-a416-0ef7d8c47517")).orElse(null);
        book.setAuthor(author);

        repository.save(book);
    }

    @Test
    public void addAuthorAndBookTest(){
        Book book = new Book();
        book.setIsbn("98598-55654");
        book.setGender(BookGender.FICTION);
        book.setPrice(BigDecimal.valueOf(55));
        book.setTitle("My Title 2");
        book.setDatePublished(LocalDate.of(1855, 1, 3));

        Author author = new Author();
        author.setName("Gavião Negro");
        author.setNationality("Avenger");
        author.setDateBirthday(LocalDate.of(1985, 2, 3));

        authorRepository.save(author);
        book.setAuthor(author);

        repository.save(book);
    }
}
