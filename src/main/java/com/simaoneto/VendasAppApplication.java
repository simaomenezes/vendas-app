package com.simaoneto;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
