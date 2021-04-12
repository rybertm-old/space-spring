package com.estudo.space.controller;

import java.util.List;

import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.repository.SatelliteRepository;
import com.estudo.space.service.SatelliteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/satellites")
@RequiredArgsConstructor
public class SatelliteController {
    private final SatelliteService service;
    private final SatelliteRepository repo;

    @GetMapping
    public ResponseEntity<List<Satellite>> getAll() {
        return ResponseEntity.ok().body(repo.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Satellite> edit(@PathVariable Integer id, @RequestBody SatelliteDTO newSat) {
        return ResponseEntity.ok().body(service.editSatellite(id, newSat));
    }
}
