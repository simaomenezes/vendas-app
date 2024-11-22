package io.github.simaomenezes.libraryapi.controller.mappers;
import io.github.simaomenezes.libraryapi.controller.dto.AuthorDTO;
import io.github.simaomenezes.libraryapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "dateBirthday", target = "dateBirthday")
    @Mapping(source = "nationality", target = "nationality")
    Author toEntity(AuthorDTO authorDTO);

    AuthorDTO toDTO(Author author);
}
