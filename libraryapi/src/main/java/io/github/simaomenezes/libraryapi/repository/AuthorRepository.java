package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
