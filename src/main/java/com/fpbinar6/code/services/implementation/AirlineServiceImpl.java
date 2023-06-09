package com.fpbinar6.code.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.repository.AirlineRepository;
import com.fpbinar6.code.services.AirlineService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    
    final AirlineRepository airlineRepository;

    @Override
    public List<Airline> getAllAirline() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline getAirlineById(int id) {
        return null;
    }

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Airline airline) {
        return null;
    }

    @Override
    public void deleteAirlineById(int id) {
        
    }
}
