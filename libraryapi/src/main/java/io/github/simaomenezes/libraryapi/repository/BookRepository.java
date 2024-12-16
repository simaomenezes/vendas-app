package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    // Query Method:
    /*
    *   1 - select * from book were id_author = id
    *   2 - select * from book where title = title
    *   3 - select * from book where isbn = ?
    *   4 - select * from book where title = ? and price = ?
    *   5 - select * from book where title = ? or isbn = ? order by title
    *   6 - select * from book where date_published between ? and ?
    *   7 - JPQL -> referencia as entidades e as propriedades - select l.* from book as l order by l.title
    *   8 - select a.* from book l join author a on a.id = l.id_author
    *   9 - select distinct l.* from book l
    *   10 -
    *   11 - named parameters -> parametros nomeados
    *   12 - positional parameters
    *   13 -
     * */

    /*1*/
    Page<Book> findByAuthor(Author author, Pageable pageable);

    /*2*/
    List<Book> findByTitle(String title);

    /*3*/
    Optional<Book> findByIsbn(String isbn);

    /*4*/
    List<Book> findByTitleAndPrice(String title, BigDecimal price);

    /*5*/
    List<Book> findByTitleOrIsbnOrderByTitle(String title, String isbn);

    /*6*/
    List<Book> findByDatePublishedBetween(LocalDate begin, LocalDate end);

    /*7*/
    @Query("select b from Book as b order by b.title, b.price")
    List<Book> findAllBooksOrderByTitleAndPrice();

    /*8*/
    @Query("select a from Book l join l.author a ")
    List<Author> findAllAutorsOfBooks();

    /*9*/
    @Query("select distinct l.title from Book l")
    List<String> listDistinctTitleBook();

    /*10*/
    @Query("""
        select l.gender
        from Book l
        join l.author a
        where a.nationality = 'Brasileira'
        order by l.gender
    """)
    List<String> listGenderAuthorBrasilian();

    /*11*/
    @Query("select l from Book l where l.gender = :gender order by :parameterToOrder ")
    List<Book> findByGender(
            @Param("gender") BookGender bookGender,
            @Param("parameterToOrder") String namePropertied
    );

    /*12*/
    @Query("select l from Book l where l.gender = ?2 order by ?1 ")
    List<Book> findByGenderPositionalParameters(String namePropertied, BookGender bookGender);

    /*13*/
    @Modifying
    @Transactional
    @Query(" delete from Book where gender = ?1 ")
    void deleteByGender(BookGender gender);

    /*14*/
    @Modifying
    @Transactional
    @Query(" update Book set datePublished = ?1 ")
    void updateDatePublished(LocalDate newDate);

    boolean existsByAuthor(Author author);

    List<Book> findByAuthor(Author authorFound);
}
