package com.estudo.space.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Planet")
@Table(name = "planet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double mass;

    @JsonManagedReference
    @OneToOne(mappedBy = "planet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Gravity gravity;

    @JsonManagedReference
    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Satellite> satellites = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "planet_galaxy", joinColumns = {
            @JoinColumn(name = "id_planet", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "id_galaxy", referencedColumnName = "id", nullable = false) })
    private List<Galaxy> galaxies = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanetStar> stars = new ArrayList<>();

    public Planet(String name, Double mass) {
        this.name = name;
        this.mass = mass;
    }

    // Sincronizar a associação
    // https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
    public void addSatellite(Satellite sat) {
        satellites.add(sat);
        sat.setPlanet(this);
    }

    public void addStar(Star st) {
        PlanetStar ps = new PlanetStar(this, st);
        stars.add(ps);
        st.getPlanets().add(ps);
    }

    public void removeSatellite(Satellite sat) {
        satellites.remove(sat);
        sat.setPlanet(null);
    }

    public void removeStar(Star st) {
        for (Iterator<PlanetStar> ite = stars.iterator(); ite.hasNext();) {
            PlanetStar ps = ite.next();

            // Procura PlanetStar que possui a associação entre esse planet e star
            if (ps.getPlanet().equals(this) && ps.getStar().equals(st)) {
                // Remove a associação da entidade Planet atual
                ite.remove();
                // Remove a associação da entidade Star
                ps.getStar().getPlanets().remove(ps);
                // Remove referência a Planet e Star de PlanetStar para o JPA poder remover
                // essa coluna da tabela
                ps.setPlanet(null);
                ps.setStar(null);
                System.out.println("Removido!");
            }
        }
    }
}
