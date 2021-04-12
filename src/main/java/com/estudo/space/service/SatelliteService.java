package com.estudo.space.service;

import java.util.Optional;

import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.repository.SatelliteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SatelliteService {
    private final SatelliteRepository repo;

    public Satellite editSatellite(int id, SatelliteDTO newSat) throws IllegalArgumentException {
        Optional<Satellite> st = repo.findById(id);
        Satellite sst = st
                .orElseThrow(() -> new IllegalArgumentException(String.format("Satellite with ID %d not found", id)));
        sst.setName(newSat.getName());
        sst.setIsNatural(newSat.getNatural());
        return repo.save(sst);
    }
}
