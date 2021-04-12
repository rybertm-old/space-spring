package com.estudo.space.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.Getter;

@Entity(name = "PlanetView")
@Immutable
@Table(name = "vw_planet")
public class PlanetView {
    @Getter
    @Id
    private Integer id;

    @Getter
    private String name;

    @Getter
    private Double radiation;
}
