package com.estudo.space.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.estudo.space.domain.dto.SatelliteDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Satellite")
@Table(name = "satellite")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @JsonProperty("natural")
    @Column(nullable = false)
    private Boolean isNatural;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planet")
    private Planet planet;

    public Satellite(String name, Boolean nat) {
        this.name = name;
        this.isNatural = nat;
    }

    public Satellite(SatelliteDTO dto) {
        this.name = dto.getName();
        this.isNatural = dto.getNatural();
    }
}
