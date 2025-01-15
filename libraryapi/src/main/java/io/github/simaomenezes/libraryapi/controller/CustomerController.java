package io.github.simaomenezes.libraryapi.controller;

import io.github.simaomenezes.libraryapi.model.Customer;
import io.github.simaomenezes.libraryapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody Customer client){
        log.info("Created new customer: {} with scope: {} ", client.getCustomeId(), client.getScope());
        service.add(client);
    }
}
