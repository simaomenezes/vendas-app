package io.github.simaomenezes.libraryapi.service;

import io.github.simaomenezes.libraryapi.model.Customer;
import io.github.simaomenezes.libraryapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public Customer add(Customer customer){
        String passwordCript = encoder.encode(customer.getCustomeSecret());
        customer.setCustomeSecret(passwordCript);
        return repository.save(customer);
    }

    public Customer findByCustomerId(String customerId){
        return repository.findByCustomerId(customerId);
    }
}
