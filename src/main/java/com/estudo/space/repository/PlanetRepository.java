package com.estudo.space.repository;

import com.estudo.space.domain.Planet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
