package com.fpbinar6.code.services.implementation;

import java.util.List;
import java.util.Optional;

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
        return airlineRepository.findById(id).orElseThrow();
    }

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public Airline updateAirline(Airline updateAirline) {
        Optional<Airline> result = airlineRepository.findById(updateAirline.getAirlineId());
        Airline airline;
        if(result.isPresent()){
            airline = result.get();
            airline.setName(updateAirline.getName());
            return airlineRepository.save(airline);
        }
        else {
            throw new RuntimeException("Data tidak ditemukan");
        }
    }

    @Override
    public void deleteAirlineById(int id) {
        Optional<Airline> result = airlineRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Data tidak ditemukan");
        }
        airlineRepository.delete(result.get());
    }
}
