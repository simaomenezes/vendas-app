package io.github.simaomenezes.libraryapi.controller.dto;

import io.github.simaomenezes.libraryapi.model.Author;

import java.time.LocalDate;

public record AuthorDTO(
        String name,
        LocalDate dateBirthday,
        String nationality) {

    public Author mapperToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setDateBirthday(this.dateBirthday);
        author.setNationality(this.nationality);

        return author;
    }
}
