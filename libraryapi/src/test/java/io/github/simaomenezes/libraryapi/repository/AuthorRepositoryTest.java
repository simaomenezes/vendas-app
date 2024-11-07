package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;

import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void addTest(){
        Author author = new Author();
        author.setName("Lucas");
        author.setNationality("Brazilian");
        author.setDateBirthday(LocalDate.of(1985,12, 10));
        Author authorSalved = repository.save(author);
        System.out.println("Author Salved: " + authorSalved);
    }

    @Test
    public void updateById(){
        var id = UUID.fromString("92b39bb3-4145-4a74-8e0a-5cfa87baded8");
        Optional<Author> authorFound = repository.findById(id);
        if(authorFound.isPresent()){
            System.out.println("Author found: " + authorFound.get().getName());
            authorFound.get().setName("Lucas Lima");
            System.out.println("Author change: " + authorFound.get().getName());
            repository.save(authorFound.get());
        }
    }

    @Test
    public void getAllTest(){
        List<Author> authorList = repository.findAll();
        authorList.forEach(System.out::println);
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("5caf49b0-987b-4b1b-96df-55a3658a981c");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("92b39bb3-4145-4a74-8e0a-5cfa87baded8");
        var author = repository.findById(id).get();
        repository.delete(author);
    }

    @Test
    public void countTest(){
        System.out.println("Count of authores: " + repository.count());
    }

    @Test
    public void addAuthorWithBooksTest(){

        Author author = new Author();
        author.setName("Juca Lima da Silva");
        author.setNationality("Portugal");
        author.setDateBirthday(LocalDate.of(1985, 2, 3));

        Book book = new Book();
        book.setIsbn("78965-58746");
        book.setGender(BookGender.ROMANCE);
        book.setPrice(BigDecimal.valueOf(100));
        book.setTitle("Good Things");
        book.setDatePublished(LocalDate.of(1855, 1, 3));

        book.setAuthor(author);

        Book book1 = new Book();
        book1.setIsbn("1236-96325");
        book1.setGender(BookGender.MYSTERY);
        book1.setPrice(BigDecimal.valueOf(201));
        book1.setTitle("Mystery of Heaven");
        book1.setDatePublished(LocalDate.of(1855, 1, 3));

        book1.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book1);
        author.getBooks().add(book);

        repository.save(author);
        //bookRepository.saveAll(author.getBooks());


    }
}
