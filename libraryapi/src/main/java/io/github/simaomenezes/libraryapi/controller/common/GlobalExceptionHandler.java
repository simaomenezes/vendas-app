package io.github.simaomenezes.libraryapi.controller.common;

import io.github.simaomenezes.libraryapi.controller.error.ErrorField;
import io.github.simaomenezes.libraryapi.controller.error.ErrorResponse;
import io.github.simaomenezes.libraryapi.exceptions.OperationNotAllowException;
import io.github.simaomenezes.libraryapi.exceptions.RecordDuplicatedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorField> listErrors = fieldErrors
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                listErrors);
    }

    @ExceptionHandler(RecordDuplicatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRecordeDuplicateException(RecordDuplicatedException e){
        return ErrorResponse.responseConflict(e.getMessage());
    }

    @ExceptionHandler(OperationNotAllowException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOperationNotAllowException(
            OperationNotAllowException e){
        return ErrorResponse.responsePattern(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleErrors(RuntimeException e){
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Any Error. Are your need contact with administrator"
                , List.of());
    }

}
