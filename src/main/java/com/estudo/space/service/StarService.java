package com.estudo.space.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.estudo.space.domain.Star;
import com.estudo.space.domain.Planet;
import com.estudo.space.domain.PlanetStar;
import com.estudo.space.domain.dto.StarDTO;
import com.estudo.space.exception.NotFoundException;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.repository.PlanetStarRepository;
import com.estudo.space.repository.StarRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StarService {
    private final StarRepository repo;
    private final PlanetStarRepository planetService;

    private static final String INVALID_ID = "Invalid ID";

    public Star find(Integer id) throws ServiceException {
        try {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            Optional<Star> sat = repo.findById(id);

            return sat.orElseThrow(() -> new NotFoundException(String.format("Star with ID %d not found", id)));
        } catch (Exception ex) {
            throw new ServiceException("Could not find Star", ex);
        }
    }

    public List<Star> findAll() {
        return repo.findAll();
    }

    public Star create(StarDTO gal) throws ServiceException {
        try {
            Star gl = fromDTO(gal);
            return repo.save(gl);
        } catch (Exception ex) {
            throw new ServiceException("Could not create Star", ex);
        }
    }

    public void delete(Integer id) throws ServiceException {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException(INVALID_ID);
            }

            Star gl = find(id);

            repo.delete(gl);
        } catch (Exception ex) {
            throw new ServiceException("Could not delete Star", ex);
        }
    }

    public Star fromDTO(StarDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO can not be null");
        }

        return new Star(dto.getId(), dto.getName(), dto.getInfluenceRadius(), null);
    }
}
