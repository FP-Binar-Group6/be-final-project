package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.repository.AirlineRepository;
import com.fpbinar6.code.repository.AirportRepository;
import com.fpbinar6.code.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ScheduleSeeder implements CommandLineRunner {

    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleSeeder(AirportRepository airportRepository, AirlineRepository airlineRepository, ScheduleRepository scheduleRepository) {
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedScheduleData();
    }

   private void seedScheduleData() throws IOException {
    // Define the relative path to the JSON file
    String jsonFilePath = "data/schedules.json";

    // Read the JSON file from the classpath
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

    if (inputStream == null) {
        throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
    }

    // Create an ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    // Map the JSON content to a list of ScheduleRequestDTO objects
    List<ScheduleRequestDTO> scheduleDTOs = objectMapper.readValue(inputStream, new TypeReference<List<ScheduleRequestDTO>>() {});

    // Iterate through the ScheduleRequestDTO list and save the schedules to the database
    for (ScheduleRequestDTO scheduleDTO : scheduleDTOs) {
        // Retrieve Airport and Airline instances using their IDs from the ScheduleRequestDTO
        Airport departureAirport = airportRepository.findById(scheduleDTO.getDepartureAirportId()).orElse(null);
        Airport arrivalAirport = airportRepository.findById(scheduleDTO.getArrivalAirportId()).orElse(null);
        Airline airline = airlineRepository.findById(scheduleDTO.getAirlineId()).orElse(null);

        // Create Schedule instance using the ScheduleRequestDTO and related models
        Schedule schedule = scheduleDTO.toSchedule(departureAirport, arrivalAirport, airline);

        // Save the Schedule instance to the database
        scheduleRepository.save(schedule);
    }

    inputStream.close();
}

}
