package com.estudo.space.repository;

import com.estudo.space.domain.Gravity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GravityRepository extends JpaRepository<Gravity, Integer> {

}
