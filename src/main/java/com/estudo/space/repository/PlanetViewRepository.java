package com.estudo.space.repository;

import java.util.List;

import com.estudo.space.domain.PlanetView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlanetViewRepository extends JpaRepository<PlanetView, Integer> {
    @Query("SELECT pl FROM PlanetView pl")
    public List<PlanetView> findView();
}
