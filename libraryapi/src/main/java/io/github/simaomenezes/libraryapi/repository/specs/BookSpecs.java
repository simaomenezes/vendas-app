package io.github.simaomenezes.libraryapi.repository.specs;

import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.model.BookGender;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn){
        return (root, query, cb)->cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title){
        return (root, query, cb)->cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }

    public static Specification<Book> genderEqual(BookGender gender){
        return (root, query, cb)->cb.equal(root.get("gender"), gender);
    }

    public static Specification<Book> yearPublishedEqual(Integer yearPublished){
        return (root, query, cb)->cb.equal(cb.function("to_char", String.class, root.get("datePublished"), cb.literal("YYYY")), yearPublished.toString());
    }

    public static Specification<Book> nameAuthorLike(String name){
        return ((root, query, cb) -> {
            Join<Object, Object> joinAuthor = root.join("author", JoinType.INNER);
            return cb.like(cb.upper(joinAuthor.get("name")), "%" + name.toUpperCase() + "%");
        });
    }
}
