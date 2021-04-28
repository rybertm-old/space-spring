package com.estudo.space.service;

import java.util.Optional;

import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.exception.NotFoundException;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.repository.SatelliteRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SatelliteService {
    private final SatelliteRepository repo;

    public Satellite find(Integer id) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Invalid ID");
            }
            Optional<Satellite> sat = repo.findById(id);

            return sat.orElseThrow(() -> new NotFoundException(String.format("Satellite with ID %d not found", id)));
        } catch (Exception ex) {
            throw new ServiceException("Could not find Satellite", ex);
        }
    }

    public Satellite editSatellite(Integer id, SatelliteDTO newSat) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Invalid ID");
            }
            Optional<Satellite> st = repo.findById(id);
            Satellite sst = st
                    .orElseThrow(() -> new NotFoundException(String.format("Satellite with ID %d not found", id)));
            if (newSat == null) {
                throw new NotFoundException("Satellite can not be null");
            }
            sst.setName(newSat.getName());
            sst.setIsNatural(newSat.getNatural());
            return repo.save(sst);
        } catch (Exception ex) {
            throw new ServiceException("Could not edit Satellite", ex);
        }
    }
}
