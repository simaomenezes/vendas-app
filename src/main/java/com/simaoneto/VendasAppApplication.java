package com.simaoneto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasAppApplication {

    @Autowired
    @Qualifier("applicationNameFromBean")
    private String applicationNameFromBean;

    @Value("${application.name}")
    private String applicationNameFromProperties;

    @GetMapping("/hello")
    public String helloWorld(){
        return applicationNameFromProperties;
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasAppApplication.class, args);
    }
}
