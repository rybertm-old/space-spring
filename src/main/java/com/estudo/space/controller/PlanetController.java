package com.estudo.space.controller;

import java.util.List;

import com.estudo.space.domain.Planet;
import com.estudo.space.domain.PlanetView;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.service.PlanetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planets")
@RequiredArgsConstructor
public class PlanetController {
    private final PlanetService service;

    @GetMapping
    public ResponseEntity<List<Planet>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> findPlanet(@PathVariable Integer id) throws IllegalArgumentException {
        return ResponseEntity.ok().body(service.find(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addSatellite(@PathVariable Integer id, @RequestBody SatelliteDTO sat)
            throws IllegalArgumentException {
        service.addSatellite(id, sat);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
    }

    @DeleteMapping("/{id}/{satId}")
    public ResponseEntity<Void> removeSatellite(@PathVariable Integer id, @PathVariable Integer satId)
            throws IllegalArgumentException {
        service.removeSatellite(id, satId);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).build();
    }
}
