package com.fpbinar6.code.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

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
        var schedule = scheduleRepository.findById(id);
        return schedule.get().convertToResponse();
    }

    @Override
    public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO ScheduleRequest) {
        var airline = airlineRepository.findById(ScheduleRequest.getAirlineId()).orElseThrow(() -> new RuntimeException("Airline not found"));
        var departureAirport = airportRepository.findById(ScheduleRequest.getDepartureAirportId()).orElseThrow(() -> new RuntimeException("Departure Airport not found"));
        var arrivalAirport = airportRepository.findById(ScheduleRequest.getArrivalAirportId()).orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
        var schedule = ScheduleRequest.toSchedule(departureAirport, arrivalAirport, airline);
        var result = scheduleRepository.save(schedule);
        return result.convertToResponse();
    }
    
}
