package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    @Test
    public void addBookAndAuthorWithCascadeTest(){
        Book book = new Book();
        book.setIsbn("98598-51654");
        book.setGender(BookGender.FICTION);
        book.setPrice(BigDecimal.valueOf(25));
        book.setTitle("War Of Read");
        book.setDatePublished(LocalDate.of(1855, 1, 3));

        Author author = new Author();
        author.setName("Dunga Lima");
        author.setNationality("He-man");
        author.setDateBirthday(LocalDate.of(1985, 2, 3));
        author.setNationality("USA");
        book.setAuthor(author);
        repository.save(book);
    }

    @Test
    public void updateAuthorOfBookTest(){
        UUID bookId = UUID.fromString("12080043-83ec-4d1e-82ed-e4dc9e3c4b67");
        Book bookFound = repository.findById(bookId).orElse(null);

        UUID id = UUID.fromString("e0e6463b-74bd-472e-a416-0ef7d8c47517");
        Author authorFound = authorRepository.findById(id).orElse(null);
        authorFound.setName("Ricardo Lima");
        bookFound.setAuthor(authorFound);
        repository.save(bookFound);

    }

    @Test
    public void deleteTest(){
        UUID bookId = UUID.fromString("12080043-83ec-4d1e-82ed-e4dc9e3c4b67");
        repository.deleteById(bookId);
    }

    @Test
    public void deleteWithCascadeTest(){
        UUID id = UUID.fromString("54825c3d-a194-426c-85f2-9d07ad526f94");
        repository.deleteById(id);
    }

    @Test
    public void getAllTest(){
        UUID id = UUID.fromString("502301d5-0e9c-455e-aa28-df4ef73a20a2");
        Book bookFound = repository.findById(id).orElse(null);
        System.out.println("Book:");
        System.out.println(":::: - " + bookFound.getTitle());


        //System.out.println("Author:");
        //System.out.println(":::: - " + bookFound.getAuthor().getName());
    }



    @Test
    public void findByTitle(){
        var title = "War Of Read";
        List<Book> booksFound = repository.findByTitle(title);
        booksFound.forEach(System.out::println);
    }

    @Test
    public void findByIsbn(){
        var isbn = "";
        Optional<Book> booksFound = repository.findByIsbn(isbn);
        booksFound.stream().forEach(System.out::println);

    }

    @Test
    public void findByTitleAndPrice(){
        var title = "Mystery of Heaven";
        var price = BigDecimal.valueOf(201.00);
        List<Book> booksFound = repository.findByTitleAndPrice(title, price);
        booksFound.forEach(System.out::println);
    }

    @Test
    public void findByTitleOrIsbn(){
        var title = "WE PO";
        var isbn = "98598-51654";
        List<Book> booksFound = repository.findByTitleOrIsbnOrderByTitle(title, isbn);
        booksFound.forEach(System.out::println);
    }
}
