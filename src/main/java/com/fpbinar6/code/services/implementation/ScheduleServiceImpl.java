package com.fpbinar6.code.services.implementation;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ScheduleResponseDTO> searchSchedules(Timestamp departureTime, Integer departureAirportId,
            Integer arrivalAirportId) {
        Timestamp departureTimeStart = null;
        Timestamp departureTimeEnd = null;

        if (departureTime != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureTime);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            departureTimeStart = new Timestamp(calendar.getTimeInMillis());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            departureTimeEnd = new Timestamp(calendar.getTimeInMillis());
        }

        List<Schedule> result = scheduleRepository.searchSchedules(departureTime, departureTimeStart,departureTimeEnd, departureAirportId,
                arrivalAirportId);

        return result.stream()
                .map(Schedule::convertToResponse)
                .collect(Collectors.toList());
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
        var airline = airlineRepository.findById(scheduleRequest.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found"));
        var departureAirport = airportRepository.findById(scheduleRequest.getDepartureAirportId())
                .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
        var arrivalAirport = airportRepository.findById(scheduleRequest.getArrivalAirportId())
                .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
        var schedule = scheduleRequest.toSchedule(departureAirport, arrivalAirport, airline);
        var result = scheduleRepository.save(schedule);
        return result.convertToResponse();
    }

    @Override
    public void deleteScheduleById(int id) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Data tidak ditemukan");
        }
        scheduleRepository.delete(result.get());
    }

}
