package com.simaoneto;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VendaAppConfiguration {

    @Bean(name = "applicationNameFromBean")
    public String applicationNameFromBean(){
        return "Sistema de Vendas from bean";
    }
}
