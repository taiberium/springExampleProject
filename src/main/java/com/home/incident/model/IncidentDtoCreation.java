package com.home.incident.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class IncidentDtoCreation {
    @NotNull
    private LocalDate date;
    private float latitude;
    private float longitude;
    @NotNull
    private String city;
    @NotNull
    private String state;
}
