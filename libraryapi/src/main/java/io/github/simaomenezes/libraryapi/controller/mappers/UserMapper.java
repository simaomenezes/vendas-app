package io.github.simaomenezes.libraryapi.controller.mappers;

import io.github.simaomenezes.libraryapi.controller.dto.UserDTO;
import io.github.simaomenezes.libraryapi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
}
