package com.estudo.space.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.estudo.space.domain.Galaxy;
import com.estudo.space.domain.Planet;
import com.estudo.space.domain.dto.GalaxyDTO;
import com.estudo.space.exception.NotFoundException;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.repository.GalaxyRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GalaxyService {
    private final GalaxyRepository repo;
    private final PlanetService planetService;

    private static final String INVALID_ID = "Invalid ID";

    public Galaxy find(Integer id) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Galaxy> sat = repo.findById(id);

            return sat.orElseThrow(() -> new NotFoundException(String.format("Galaxy with ID %d not found", id)));
        } catch (Exception ex) {
            throw new ServiceException("Could not find Galaxy", ex);
        }
    }

    public List<Galaxy> findAll() {
        return repo.findAll();
    }

    public Galaxy create(GalaxyDTO gal) throws ServiceException {
        try {
            Galaxy gl = fromDTO(gal);
            return repo.save(gl);
        } catch (ServiceException ex) {
            throw new ServiceException("Could not create galaxy", ex);
        }
    }

    public void delete(Integer id) throws ServiceException {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }

            Galaxy gl = find(id);

            repo.delete(gl);
        } catch (Exception ex) {
            throw new ServiceException("Could not delete Galaxy", ex);
        }
    }

    public Galaxy editGalaxy(Integer id, GalaxyDTO newGal) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Galaxy> gl = repo.findById(id);
            Galaxy ggl = gl.orElseThrow(() -> new NotFoundException(String.format("Galaxy with ID %d not found", id)));
            if (newGal == null) {
                throw new NotFoundException("Galaxy can not be null");
            }
            ggl.setName(newGal.getName());
            ggl.setNumberOfPlanets(newGal.getNumberOfPlanets());

            if (newGal.getPlanetIds() != null) {
                List<Planet> pl = newGal.getPlanetIds().stream().map(plId -> {
                    try {
                        return planetService.find(plId);
                    } catch (ServiceException ex) {
                        Throwable th = ex.getCause();
                        String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
                        System.out.println(err);
                    }
                    return null;
                }).collect(Collectors.toList());

                if (pl.contains(null)) {
                    throw new NotFoundException("A planet could not be found");
                }

                ggl.setPlanets(pl);
            }

            return repo.save(ggl);
        } catch (Exception ex) {
            throw new ServiceException("Could not edit Galaxy", ex);
        }
    }

    private Galaxy fromDTO(GalaxyDTO gal) throws ServiceException {
        Galaxy gl = new Galaxy(null, gal.getName(), gal.getNumberOfPlanets(), null);

        try {
            if (gal.getPlanetIds() != null) {
                List<Planet> pl = gal.getPlanetIds().stream().map(plId -> {
                    try {
                        return planetService.find(plId);
                    } catch (ServiceException ex) {
                        Throwable th = ex.getCause();
                        String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
                        System.out.println(err);
                    }
                    return null;
                }).collect(Collectors.toList());

                if (pl.contains(null)) {
                    throw new NotFoundException("A planet could not be found");
                }

                gl.setPlanets(pl);
            }
        } catch (Exception ex) {
            throw new ServiceException("Could not convert DTO to Galaxy", ex);
        }

        return gl;
    }
}
