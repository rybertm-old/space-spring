package com.estudo.space.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Gravity")
@Table(name = "gravity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gravity {
    @Id
    private Integer id;

    @Column(nullable = false)
    private Double force;

    @Column(nullable = false)
    private Double radiation;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planet")
    // ID do planeta será chave primaria e estrangeira da gravidade
    @MapsId
    private Planet planet;

    public Gravity(Double force, Double rad) {
        this.force = force;
        this.radiation = rad;
    }
}
