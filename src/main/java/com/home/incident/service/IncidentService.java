package com.home.incident.service;

import com.home.incident.model.Incident;
import com.home.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public Incident save(Incident incident) {
        return incidentRepository.save(incident);
    }

    public List<Incident> findAll(List<String> cities, @Nullable LocalDate date, @Nullable Boolean nullableSortByDateAsc) {
        Sort sortOrder = Optional.ofNullable(nullableSortByDateAsc)
                .map(sortByDateAsc ->
                        Sort.by(
                                sortByDateAsc ? Sort.Order.asc("id") : Sort.Order.desc("id"),
                                Sort.Order.desc("id")
                        )
                ).orElse(Sort.by("id"));

        if (date != null && !cities.isEmpty()) {
            return incidentRepository.findAllByDateAndCityInIgnoreCase(date, cities, sortOrder);
        }

        if (date != null) {
            return incidentRepository.findAllByDate(date, sortOrder);
        }

        if (!cities.isEmpty()) {
            return incidentRepository.findAllByCityInIgnoreCase(cities, sortOrder);
        }

        return incidentRepository.findAll(sortOrder);

    }

    public Optional<Incident> findById(int id) {
        return incidentRepository.findById(id);
    }

}
