package com.fpbinar6.code.services;

import java.util.List;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.User;

public interface AirlineService {

    public List<Airline> getAllAirline();
    public Airline getAirlineById(int id);
    public Airline saveAirline(Airline airline);
    public Airline updateAirline(Airline airline);
    public void deleteAirlineById(int id);
    
}
