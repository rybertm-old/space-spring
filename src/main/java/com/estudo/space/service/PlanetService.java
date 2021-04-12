package com.estudo.space.service;

import java.util.List;
import java.util.Optional;

import com.estudo.space.domain.Planet;
import com.estudo.space.domain.PlanetView;
import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.repository.PlanetRepository;
import com.estudo.space.repository.PlanetViewRepository;
import com.estudo.space.repository.SatelliteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlanetService {
    private final PlanetRepository repo;
    private final PlanetViewRepository viewRepo;
    private final SatelliteRepository satRepo;

    public Planet find(int id) throws IllegalArgumentException {
        Optional<Planet> pl = repo.findById(id);

        return pl.orElseThrow(IllegalArgumentException::new);
    }

    public List<Planet> findAll() {
        return repo.findAll();
    }

    public void addSatellite(int id, SatelliteDTO sat) throws IllegalArgumentException {
        Planet pl = find(id);
        Satellite st = satRepo.save(new Satellite(null, sat.getName(), sat.getNatural(), pl));
        pl.addSatellite(st);
    }

    public void removeSatellite(int id, int satId) throws IllegalArgumentException {
        Planet pl = find(id);
        Optional<Satellite> st = satRepo.findById(satId);
        pl.removeSatellite(st.orElseThrow(IllegalArgumentException::new));
    }
}
