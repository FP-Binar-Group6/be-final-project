package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.repository.AirportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Order(2)
public class AirportSeeder implements CommandLineRunner {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportSeeder(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAirportData();
    }

    private void seedAirportData() throws IOException {
        // Define the relative path to the JSON file
        String jsonFilePath = "data/airports.json";

        // Read the JSON file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

        if (inputStream == null) {
            throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
        }

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of Airport objects
        List<Airport> airports = objectMapper.readValue(inputStream, new TypeReference<List<Airport>>() {});

        // Upsert the airports to the database
        for (Airport airport : airports) {
            // Check if an airline with the same ID exists in the database
            Airport existingAirport = airportRepository.findById(airport.getAirportId()).orElse(null);
            
            if (existingAirport != null) {
                // Update the existing airline with the new data
                existingAirport.setCode(airport.getCode());
                existingAirport.setName(airport.getName());
                existingAirport.setCityName(airport.getCityName());
                
                airportRepository.save(existingAirport);
            } else {
                // Save the new airline to the database
                airportRepository.save(airport);
            }
        }

        inputStream.close();
    }

}
