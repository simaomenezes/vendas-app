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
}
