package io.github.simaomenezes.libraryapi.controller.mappers;

import io.github.simaomenezes.libraryapi.controller.dto.BookDTO;
import io.github.simaomenezes.libraryapi.controller.dto.ResponseSearchBookDTO;
import io.github.simaomenezes.libraryapi.model.Book;
import io.github.simaomenezes.libraryapi.repository.AuthorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    AuthorRepository authorRepository;

    @Mapping(target = "author", expression = "java( authorRepository.findById(dto.idAuthor()).orElse(null) )")
    public abstract Book toEntity(BookDTO dto);

    public abstract ResponseSearchBookDTO toDTO(Book book);
}
