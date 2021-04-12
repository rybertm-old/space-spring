package com.estudo.space.repository;

import com.estudo.space.domain.Star;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {

}
