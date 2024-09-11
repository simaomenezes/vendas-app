package com.simaoneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
