package com.home.incident.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class IncidentDto {
    private int id;
    private LocalDate date;
    private float latitude;
    private float longitude;
    private String city;
    private String state;
}
