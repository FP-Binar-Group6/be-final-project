package com.fpbinar6.code.repository;

import com.fpbinar6.code.models.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
