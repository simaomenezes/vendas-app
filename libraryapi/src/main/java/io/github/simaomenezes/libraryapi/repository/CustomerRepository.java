package io.github.simaomenezes.libraryapi.repository;

import io.github.simaomenezes.libraryapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByCustomerId(String customerId);
}
