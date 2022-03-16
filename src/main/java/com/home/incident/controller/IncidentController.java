package com.home.incident.controller;

import com.home.incident.mapper.IncidentDtoMapper;
import com.home.incident.model.Incident;
import com.home.incident.model.IncidentDtoCreation;
import com.home.incident.model.IncidentDto;
import com.home.incident.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("incident")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
    private final IncidentDtoMapper incidentDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncidentDto save(@RequestBody @Valid IncidentDtoCreation incidentDtoCreation) {
        Incident incident = incidentDtoMapper.map(incidentDtoCreation);
        Incident savedIncident = incidentService.save(incident);
        return incidentDtoMapper.map(savedIncident);
    }

    //Why task don't tell about pagination params?
    @GetMapping
    public ResponseEntity<List<IncidentDto>> findAll(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(name = "cities", required = false) List<String> nullableCities,
            @RequestParam(name = "sortOrder", required = false) String nullableSortOrder
    ) {
        Boolean nullableSortByDateAsc = Optional.ofNullable(nullableSortOrder)
                .map("date"::equalsIgnoreCase)
                .orElse(null);
        List<String> cities = nullableCities == null ? Collections.emptyList() : nullableCities;
        List<Incident> incidents = incidentService.findAll(cities, date, nullableSortByDateAsc);
        List<IncidentDto> incidentDtoCreationList = incidentDtoMapper.map(incidents);
        return ResponseEntity.ok(incidentDtoCreationList);
    }

    @GetMapping("{id}")
    public ResponseEntity<IncidentDto> findById(@PathVariable("id") int id) {
        Optional<Incident> optionalIncident = incidentService.findById(id);
        return optionalIncident
                .map(incidentDtoMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
