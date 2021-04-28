package com.estudo.space.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.estudo.space.domain.Satellite;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.exception.ServiceException;
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
@RequestMapping("/satellite")
@RequiredArgsConstructor
public class SatelliteController {
    private final SatelliteService service;
    private final SatelliteRepository repo;

    @GetMapping
    public ResponseEntity<List<Satellite>> getAll() {
        return ResponseEntity.ok().body(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable @NotNull Integer id) {
        try {
            return ResponseEntity.ok().body(service.find(id));
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable @NotNull Integer id, @Valid @RequestBody SatelliteDTO newSat) {
        try {
            service.editSatellite(id, newSat);
            return ResponseEntity.ok().body("Satellite edited sucessfully");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
