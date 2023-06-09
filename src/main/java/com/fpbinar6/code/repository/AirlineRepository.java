package com.fpbinar6.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    
}
