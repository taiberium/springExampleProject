package com.home.incident.mapper;


import com.home.incident.model.Incident;
import com.home.incident.model.IncidentDto;
import com.home.incident.model.IncidentDtoCreation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IncidentDtoMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public IncidentDto map(Incident incident) {
        return modelMapper.map(incident, IncidentDto.class);
    }

    public List<IncidentDto> map(List<Incident> incidents) {
        return incidents.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Incident map(IncidentDtoCreation incidentDtoCreation) {
        return modelMapper.map(incidentDtoCreation, Incident.class);
    }
}
