package io.github.simaomenezes.localizacao.repository;

import io.github.simaomenezes.localizacao.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
