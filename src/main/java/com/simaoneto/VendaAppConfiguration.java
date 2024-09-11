package com.simaoneto;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class VendaAppConfiguration {

    /*
        @Bean(name = "applicationNameFromBean")
        public String applicationNameFromBean(){
            return "Sistema de Vendas from bean";
        }
    */

    @Bean
    public CommandLineRunner execute(){
        return args -> {
            System.out.printf("Running in dev");
        };
    }
}
