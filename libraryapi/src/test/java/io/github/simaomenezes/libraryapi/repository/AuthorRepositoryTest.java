package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

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
        var id = UUID.fromString("95612138-7789-481b-ba39-5e7040c7cfbb");
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
}
