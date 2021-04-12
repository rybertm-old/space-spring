package com.estudo.space.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Galaxy")
@Table(name = "galaxy")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Galaxy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long numberOfPlanets;

    @JsonBackReference
    @ManyToMany(mappedBy = "galaxies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Planet> planets;

    public Galaxy(String name, Long num) {
        this.name = name;
        this.numberOfPlanets = num;
    }
}
