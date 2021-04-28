package com.estudo.space.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.estudo.space.domain.Planet;
import com.estudo.space.domain.PlanetView;
import com.estudo.space.domain.dto.SatelliteDTO;
import com.estudo.space.domain.dto.StarDTO;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.service.PlanetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planet")
@RequiredArgsConstructor
public class PlanetController {
    private final PlanetService service;

    @GetMapping
    public ResponseEntity<List<Planet>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPlanet(@PathVariable Integer id) {
        try {
            Planet pl = service.find(id);
            return ResponseEntity.ok().body(pl);
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/radiation")
    public ResponseEntity<List<PlanetView>> getAllAboveRadiation() {
        return ResponseEntity.ok().body(service.getAboveRadiation());
    }

    @PutMapping("/{id}/satellite")
    public ResponseEntity<String> addSatellite(@PathVariable @NotNull Integer id,
            @RequestBody @Valid SatelliteDTO sat) {
        try {
            service.addSatellite(id, sat);
            return ResponseEntity.ok().body("Satellite adicionado com sucesso");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.tutring();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/{id}/star")
    public ResponseEntity<String> addStar(@PathVariable @NotNull Integer id, @RequestBody @Valid StarDTO sat) {
        try {
            service.addStar(id, sat);
            return ResponseEntity.ok().body("Satellite adicionado com sucesso");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}/{satId}")
    public ResponseEntity<String> removeSatellite(@PathVariable Integer id, @PathVariable Integer satId) {
        try {
            service.removeSatellite(id, satId);
            return ResponseEntity.ok().body("Satellite removido com sucesso");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}/{starId}")
    public ResponseEntity<String> removeStar(@PathVariable Integer id, @PathVariable Integer starId) {
        try {
            service.removeStar(id, starId);
            return ResponseEntity.ok().body("Star removido com sucesso");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
