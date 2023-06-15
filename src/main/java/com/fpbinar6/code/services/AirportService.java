package com.fpbinar6.code.services;

import com.fpbinar6.code.models.Airport;

import java.util.List;

public interface AirportService {
    public List<Airport> getAllAirport();
    public Airport getAirportById(int id);
    public Airport saveAirport(Airport airport);
    public Airport updateAirport(Airport airport);
    public void deleteAirportById(int id);
}
