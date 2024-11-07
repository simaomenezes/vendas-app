package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    // Query Method:
    /*
    *   1 - select * from book were id_author = id
    *   2 - select * from book where title = title
    *   3 - select * from book where isbn = ?
    *   4 - select * from book where title = ? and price = ?
    *   5 - select * from book where title = ? or isbn = ?
    *
    * */

    //1
    List<Book> findByAuthor(Author author);

    //2
    List<Book> findByTitle(String title);

    //3
    List<Book> findByIsbn(String isbn);

    //4
    List<Book> findByTitleAndPrice(String title, BigDecimal price);

    //5
    List<Book> findByTitleOrIsbn(String title, String isbn);
}
