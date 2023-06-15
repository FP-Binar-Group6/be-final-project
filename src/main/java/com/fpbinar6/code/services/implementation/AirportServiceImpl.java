package com.fpbinar6.code.services.implementation;

import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.repository.AirportRepository;
import com.fpbinar6.code.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    final AirportRepository airportRepository;

    @Override
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportById(int id) {
        return airportRepository.findById(id).orElseThrow();
    }

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport updateAirport(Airport updateAirport) {
        Optional<Airport> result = airportRepository.findById(updateAirport.getAirportId());
        Airport airport;
        if(result.isPresent()){
            airport = result.get();
            airport.setName(updateAirport.getName());
            airport.setCityName(updateAirport.getCityName());
            return airportRepository.save(airport);
        }
        else {
            throw new RuntimeException("Data tidak ditemukan");
        }
    }

    @Override
    public void deleteAirportById(int id) {
        Optional<Airport> result = airportRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Data tidak ditemukan");
        }
        airportRepository.delete(result.get());
    }
}
