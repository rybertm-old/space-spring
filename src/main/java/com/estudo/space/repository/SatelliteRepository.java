package com.estudo.space.repository;

import com.estudo.space.domain.Satellite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {

}
