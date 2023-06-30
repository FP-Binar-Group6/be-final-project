package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.repository.AirlineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Order(1)
public class AirlineSeeder implements CommandLineRunner {

    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineSeeder(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAirlineData();
    }

    private void seedAirlineData() throws IOException {
        // Define the relative path to the JSON file
        String jsonFilePath = "data/airlines.json";

        // Read the JSON file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

        if (inputStream == null) {
            throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
        }

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of Airline objects
        List<Airline> airlines = objectMapper.readValue(inputStream, new TypeReference<List<Airline>>() {});

        // Save or update the airlines in the database
        for (Airline airline : airlines) {
            // Check if an airline with the same ID exists in the database
            Airline existingAirline = airlineRepository.findById(airline.getAirlineId()).orElse(null);
            
            if (existingAirline != null) {
                // Update the existing airline with the new data
                existingAirline.setName(airline.getName());
                existingAirline.setImage(airline.getImage());
                
                airlineRepository.save(existingAirline);
            } else {
                // Save the new airline to the database
                airlineRepository.save(airline);
            }
        }

        inputStream.close();
    }
}
