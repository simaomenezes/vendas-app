package com.simaoneto.rest;

import lombok.Getter;


import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensageError) {
        this.errors = Arrays.asList(mensageError);
    }
}
