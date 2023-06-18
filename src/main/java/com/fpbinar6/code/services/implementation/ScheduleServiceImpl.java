package com.fpbinar6.code.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;
import com.fpbinar6.code.repository.AirlineRepository;
import com.fpbinar6.code.repository.AirportRepository;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.services.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    
    final ScheduleRepository scheduleRepository;
    final AirportRepository airportRepository;
    final AirlineRepository airlineRepository;

    @Override
    public List<ScheduleResponseDTO> getAllSchedule() {
        var schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> {
            return schedule.convertToResponse();
        }).toList();
    }

    @Override
    public ScheduleResponseDTO getScheduleById(int id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return schedule.get().convertToResponse();
        }
        throw new RuntimeException("Data tidak ditemukan");

    }

    @Override
    public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO scheduleRequest) {
        var airline = airlineRepository.findById(scheduleRequest.getAirlineId()).orElseThrow(() -> new RuntimeException("Airline not found"));
        var departureAirport = airportRepository.findById(scheduleRequest.getDepartureAirportId()).orElseThrow(() -> new RuntimeException("Departure Airport not found"));
        var arrivalAirport = airportRepository.findById(scheduleRequest.getArrivalAirportId()).orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
        var schedule = scheduleRequest.toSchedule(departureAirport, arrivalAirport, airline);
        var result = scheduleRepository.save(schedule);
        return result.convertToResponse();
    }

    @Override
    public void deleteScheduleById(int id) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Data tidak ditemukan");
        }
        scheduleRepository.delete(result.get());
    }
    
}
