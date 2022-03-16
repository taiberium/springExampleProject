package com.home.incident.repository;

import com.home.incident.model.Incident;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {

    List<Incident> findAllByDate(LocalDate date, Sort sort);

    List<Incident> findAllByCityInIgnoreCase(List<String> cities, Sort sort);

    List<Incident> findAllByDateAndCityInIgnoreCase(LocalDate date, List<String> cities, Sort sort);
}
