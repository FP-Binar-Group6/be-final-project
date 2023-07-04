package com.fpbinar6.code.seeder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.Seat;
import com.fpbinar6.code.models.dto.SeatSeederDTO;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.repository.SeatRepository;

@Component
@Order(1)
public class SeatSeeder implements CommandLineRunner {

    private final SeatRepository seatRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public SeatSeeder(SeatRepository seatRepository, ScheduleRepository scheduleRepository) {
        this.seatRepository = seatRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedSeatData();
    }

    private void seedSeatData() throws IOException {
        // Define the relative path to the JSON file
        String jsonFilePath = "data/seat.json";

        // Read the JSON file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

        if (inputStream == null) {
            throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
        }

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of Airline objects
        List<SeatSeederDTO> seats = objectMapper.readValue(inputStream, new TypeReference<List<SeatSeederDTO>>() {
        });

        // Save or update the airlines in the database
        for (SeatSeederDTO seat : seats) {
            // Check if an airline with the same ID exists in the database
            Seat existingSeat = seatRepository.findById(seat.getSeatId()).orElse(null);

            Schedule schedule = scheduleRepository.findById(seat.getScheduleId()).orElse(null);
            

            Seat saveSeat;
            if (existingSeat != null) {
                saveSeat = seat.toSeat(existingSeat, schedule);
            } else {
                saveSeat = seat.toSeat(schedule);
            }

            seatRepository.save(saveSeat);
            

        }

        inputStream.close();
    }
}
