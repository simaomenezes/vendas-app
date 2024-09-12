package com.simaoneto;

import com.simaoneto.domain.entity.Cliente;
import com.simaoneto.repository.Clientes;
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
            clientes.salvar(new Cliente("Menezes"));
            clientes.salvar(new Cliente("Outro Neto"));

            List<Cliente> todosClientes = clientes.getAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.getAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientes.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("deletando clientes");
            clientes.getAll().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.getAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
