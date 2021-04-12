package com.estudo.space.repository;

import com.estudo.space.domain.PlanetStar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetStarRepository extends JpaRepository<PlanetStar, Integer> {

}
