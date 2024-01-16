package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Class;
import com.fpbinar6.code.models.dto.ScheduleSeederDTO;
import com.fpbinar6.code.repository.AirlineRepository;
import com.fpbinar6.code.repository.AirportRepository;
import com.fpbinar6.code.repository.ClassRepository;
import com.fpbinar6.code.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Order(5)
public class ScheduleSeeder implements CommandLineRunner {

    private final AirportRepository airportRepository;
    private final AirlineRepository airlineRepository;
    private final ScheduleRepository scheduleRepository;
    private final ClassRepository classRepository;

    @Autowired
    public ScheduleSeeder(ClassRepository classRepository, AirportRepository airportRepository, AirlineRepository airlineRepository, ScheduleRepository scheduleRepository) {
        this.airportRepository = airportRepository;
        this.airlineRepository = airlineRepository;
        this.scheduleRepository = scheduleRepository;
        this.classRepository = classRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedScheduleData();
    }

   private void seedScheduleData() throws IOException {
    // Define the relative path to the JSON file
    String jsonFilePath = "data/schedules.json";

    // Read the JSON file from the classpath
    Resource resource = new ClassPathResource(jsonFilePath);
    InputStream inputStream = resource.getInputStream();

    if (inputStream == null) {
        throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
    }

    // Create an ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    // Map the JSON content to a list of ScheduleRequestDTO objects
    List<ScheduleSeederDTO> scheduleDTOs = objectMapper.readValue(inputStream, new TypeReference<List<ScheduleSeederDTO>>() {});

    // Iterate through the ScheduleRequestDTO list and save/update the schedules in the database
    for (ScheduleSeederDTO scheduleDTO : scheduleDTOs) {
        // Retrieve Airport and Airline instances using their IDs from the ScheduleRequestDTO
        Airport departureAirport = airportRepository.findById(scheduleDTO.getDepartureAirportId()).orElse(null);
        Airport arrivalAirport = airportRepository.findById(scheduleDTO.getArrivalAirportId()).orElse(null);
        Airline airline = airlineRepository.findById(scheduleDTO.getAirlineId()).orElse(null);
        Class kelas = classRepository.findById(scheduleDTO.getClassId()).orElse(null);
        // Check if a schedule with the same ID exists in the database
        int scheduleId = scheduleDTO.getScheduleId();
        Schedule existingSchedule = scheduleRepository.findById(scheduleId).orElse(null);

        // Create or update the Schedule instance based on the existence of the schedule in the database
        Schedule schedule;
        if (existingSchedule != null) {
            // Update the existing schedule
            schedule = scheduleDTO.toSchedule(kelas, existingSchedule, departureAirport, arrivalAirport, airline);
        } else {
            // Create a new schedule
            schedule = scheduleDTO.toSchedule(kelas, departureAirport, arrivalAirport, airline);
        }

        // Save the Schedule instance to the database
        scheduleRepository.save(schedule);
    }

    inputStream.close();
}


}
