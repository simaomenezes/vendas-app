package io.github.simaomenezes.libraryapi.exceptions;

public class OperationNotAllowException extends RuntimeException {
    public OperationNotAllowException(String msg){
        super(msg);
    }
}
