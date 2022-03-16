package com.home.incident.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Incident {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate date;

    private float latitude;
    private float longitude;
    private String city;
    private String state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Incident incident = (Incident) o;
        return id != null && Objects.equals(id, incident.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
