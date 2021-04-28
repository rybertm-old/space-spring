package com.estudo.space.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.estudo.space.domain.Gravity;
import com.estudo.space.domain.Planet;
import com.estudo.space.domain.dto.GravityDTO;
import com.estudo.space.exception.NotFoundException;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.repository.GravityRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GravityService {
    private final GravityRepository repo;
    private final PlanetService planetService;

    private static final String INVALID_ID = "Invalid ID";

    public Gravity find(Integer id) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Gravity> gra = repo.findById(id);

            return gra.orElseThrow(() -> new NotFoundException(String.format("Gravity with ID %d not found", id)));
        } catch (Exception ex) {
            throw new ServiceException("Could not find Gravity", ex);
        }
    }

    public List<Gravity> findAll() {
        return repo.findAll();
    }

    public Gravity create(GravityDTO gr) throws ServiceException {
        try {
            Gravity gra = fromDTO(gr);
            return repo.save(gra);
        } catch (ServiceException ex) {
            throw new ServiceException("Could not create Gravity", ex);
        }
    }

    public void delete(Integer id) throws ServiceException {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }

            Gravity gr = find(id);

            repo.delete(gr);
        } catch (Exception ex) {
            throw new ServiceException("Could not delete Gravity", ex);
        }
    }

    public Gravity editGravity(Integer id, GravityDTO newGal) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Gravity> gr = repo.findById(id);
            Gravity gra = gr
                    .orElseThrow(() -> new NotFoundException(String.format("Gravity with ID %d not found", id)));
            if (newGal == null) {
                throw new NotFoundException("Gravity can not be null");
            }
            gra.setForce(newGal.getForce());
            gra.setRadiation(newGal.getRadiation());

            if (newGal.getPlanetId() != null) {
                Planet pl = planetService.find(newGal.getPlanetId());

                gra.setPlanet(pl);
            }

            return repo.save(gra);
        } catch (Exception ex) {
            throw new ServiceException("Could not edit Gravity", ex);
        }
    }

    private Gravity fromDTO(GravityDTO gra) throws ServiceException {
        Gravity gr = new Gravity(null, gra.getForce(), gra.getRadiation(), null);

        try {
            if (gra.getPlanetId() != null) {
                Planet pl = planetService.find(gra.getPlanetId());

                gr.setPlanet(pl);
            }
        } catch (Exception ex) {
            throw new ServiceException("Could not convert DTO to Gravity", ex);
        }

        return gr;
    }
}
