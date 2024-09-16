package com.simaoneto.res.controller;

import com.simaoneto.exception.RegraNegocioException;
import com.simaoneto.rest.ApiErrors;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    public ApiErrors handleRegraNegocioException(RegraNegocioException exception){
        String mensageError = exception.getMessage();
        return new ApiErrors(mensageError);
    }
}
