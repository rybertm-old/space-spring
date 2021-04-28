package com.estudo.space.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Star")
@Table(name = "star")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Column(nullable = false)
    private Double influenceRadius;

    @JsonManagedReference
    @OneToMany(mappedBy = "star", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PlanetStar> planets = new ArrayList<>();

    public Star(String name, Double infRad) {
        this.name = name;
        this.influenceRadius = infRad;
    }
}
