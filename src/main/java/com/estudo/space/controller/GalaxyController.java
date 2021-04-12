package com.estudo.space.controller;

import java.util.List;

import com.estudo.space.domain.Galaxy;
import com.estudo.space.repository.GalaxyRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/galaxies")
@RequiredArgsConstructor
public class GalaxyController {
    private final GalaxyRepository repo;

    @GetMapping
    public ResponseEntity<List<Galaxy>> getAll() {
        return ResponseEntity.ok().body(repo.findAll());
    }
}
