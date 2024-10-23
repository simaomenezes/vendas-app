package io.github.simaomenezes.localizacao;

import io.github.simaomenezes.localizacao.domain.entity.City;
import io.github.simaomenezes.localizacao.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CityService cityService;


	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cityService.save();
		cityService.getAll();


	}
}
