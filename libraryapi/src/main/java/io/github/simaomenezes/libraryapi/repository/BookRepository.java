package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
