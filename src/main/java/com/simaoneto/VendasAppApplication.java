package com.simaoneto;

import com.simaoneto.domain.entity.Cliente;

import com.simaoneto.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasAppApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Fulano"));
            clientes.save(new Cliente("Outro Cliente"));

            boolean existe = clientes.existsByNome("Neto");
            System.out.println("existe um cliente com o nome Neto? " + existe);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
