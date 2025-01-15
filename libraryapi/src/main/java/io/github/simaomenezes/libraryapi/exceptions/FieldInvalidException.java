package io.github.simaomenezes.libraryapi.exceptions;

import lombok.Getter;

public class FieldInvalidException extends RuntimeException {

    @Getter
    private String field;

    public FieldInvalidException(String field, String msg){
        super(msg);
        this.field = field;
    }
}
