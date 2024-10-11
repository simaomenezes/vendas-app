package io.github.simaomenezes.localizacao.service;

import io.github.simaomenezes.localizacao.domain.entity.City;
import io.github.simaomenezes.localizacao.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public void save(){
        var city = new City(1L, "Lisboa", 12123324L);
        repository.save(city);
    }

    public List<City> getAll(){
        return repository.findAll();
    }
}
