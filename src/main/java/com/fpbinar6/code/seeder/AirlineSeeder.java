package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.repository.AirlineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
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

        // Read the JSON file
        Path path = new ClassPathResource(jsonFilePath).getFile().toPath();
        String jsonContent = Files.readString(path);

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of Airline objects
        List<Airline> airlines = objectMapper.readValue(jsonContent, new TypeReference<List<Airline>>() {});

        // Save the airlines to the database
        airlineRepository.saveAll(airlines);
    }
}

