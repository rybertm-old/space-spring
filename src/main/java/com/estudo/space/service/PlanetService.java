package com.estudo.space.service;

import java.util.List;
import java.util.Optional;

import com.estudo.space.domain.Planet;
import com.estudo.space.domain.PlanetView;
import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.Star;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.domain.dto.StarDTO;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.repository.PlanetRepository;
import com.estudo.space.repository.PlanetViewRepository;
import com.estudo.space.repository.SatelliteRepository;
import com.estudo.space.repository.StarRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlanetService {
    private final PlanetRepository repo;
    private final PlanetViewRepository viewRepo;
    private final SatelliteRepository satRepo;
    private final StarRepository starRepo;

    private static final String INVALID_ID = "Invalid ID";

    public Planet find(Integer id) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Planet> pl = repo.findById(id);

            return pl.orElseThrow(() -> new IllegalArgumentException("Could not find Planet with ID:" + id));
        } catch (Exception ex) {
            throw new ServiceException("Could not find Planet", ex);
        }
    }

    public List<Planet> findAll() {
        return repo.findAll();
    }

    public List<PlanetView> getAboveRadiation() {
        return viewRepo.findView();
    }

    public Satellite addSatellite(Integer id, SatelliteDTO sat) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Planet pl = find(id);
            if (sat == null) {
                throw new IllegalArgumentException("Satellite can not be null");
            }
            Satellite st = satRepo.save(new Satellite(null, sat.getName(), sat.getNatural(), pl));
            pl.addSatellite(st);
            return st;
        } catch (Exception ex) {
            throw new ServiceException("Could not add Satellite to Planet", ex);
        }
    }

    public Star addStar(Integer id, StarDTO star) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Planet pl = find(id);
            if (star == null) {
                throw new IllegalArgumentException("Star can not be null");
            }
            Star st = starRepo.save(new Star(null, star.getName(), star.getInfluenceRadius(), null));
            pl.addStar(st);
            return st;
        } catch (Exception ex) {
            throw new ServiceException("Could not add Star to Planet", ex);
        }
    }

    public void removeSatellite(Integer id, Integer satId) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Planet pl = find(id);

            if (satId == null || satId <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Satellite st = satRepo.findById(satId)
                    .orElseThrow(() -> new IllegalArgumentException("Could not find Satellite with ID: " + satId));
            pl.removeSatellite(st);
            satRepo.delete(st);
        } catch (ServiceException ex) {
            throw new ServiceException("Could not remove Satellite from Planet", ex.getEx());
        } catch (Exception ex) {
            throw new ServiceException("Could not remove Satellite from Planet", ex);
        }
    }

    public void removeStar(Integer id, Integer starId) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Planet pl = find(id);

            if (starId == null || starId <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Star st = starRepo.findById(starId)
                    .orElseThrow(() -> new IllegalArgumentException("Could not find Star with ID: " + starId));
            pl.removeStar(st);
            starRepo.delete(st);
        } catch (ServiceException ex) {
            throw new ServiceException("Could not remove Star from Planet", ex.getEx());
        } catch (Exception ex) {
            throw new ServiceException("Could not remove Star from Planet", ex);
        }
    }
}
