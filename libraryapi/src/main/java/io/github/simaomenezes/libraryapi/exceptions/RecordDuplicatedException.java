package io.github.simaomenezes.libraryapi.exceptions;

public class RecordDuplicatedException extends RuntimeException {
    public RecordDuplicatedException(String msg){
        super(msg);
    }
}
