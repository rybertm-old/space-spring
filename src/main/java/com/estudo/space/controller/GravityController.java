package com.estudo.space.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.estudo.space.domain.Gravity;
import com.estudo.space.domain.dto.GravityDTO;
import com.estudo.space.exception.ServiceException;
import com.estudo.space.service.GravityService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gravity")
@RequiredArgsConstructor
public class GravityController {
    private final GravityService service;

    @GetMapping
    public ResponseEntity<List<Gravity>> getAll() {
        return ResponseEntity.ok().body(service.findAll());
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

    @PostMapping
    public ResponseEntity<String> create(@Valid GravityDTO dto) {
        try {
            service.create(dto);
            return ResponseEntity.ok().body("Gravity created.");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable @NotNull Integer id, @Valid GravityDTO dto) {
        try {
            return ResponseEntity.ok().body(service.editGravity(id, dto));
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().body("Gravity deleted.");
        } catch (ServiceException ex) {
            Throwable th = ex.getCause();
            String err = "\nErr: " + ex.getMessage() + ",\nCause: " + th.toString();
            System.out.println(err);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
