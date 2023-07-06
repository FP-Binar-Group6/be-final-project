package com.fpbinar6.code.services.implementation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fpbinar6.code.models.Schedule;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;
import com.fpbinar6.code.repository.AirlineRepository;
import com.fpbinar6.code.repository.AirportRepository;
import com.fpbinar6.code.repository.ClassRepository;
import com.fpbinar6.code.repository.ScheduleRepository;
import com.fpbinar6.code.repository.SeatRepository;
import com.fpbinar6.code.services.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    final ScheduleRepository scheduleRepository;
    final AirportRepository airportRepository;
    final AirlineRepository airlineRepository;
    final SeatRepository seatRepository;
    final ClassRepository classRepository;

    @Override
    public List<ScheduleResponseDTO> getAllSchedule() {
        var schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> {
            return schedule.convertToResponse();
        }).toList();
    }

    @Override
    public List<ScheduleResponseDTO> searchSchedules(Timestamp departureTime, Integer departureAirportId,
            Integer arrivalAirportId, Integer airlineId, String className) {
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
        if (className == null || className.isEmpty() || className.equals("")) {
            List<Schedule> result = scheduleRepository.searchSchedulesWithoutClassName(departureTime, departureTimeStart,
                    departureTimeEnd,
                    departureAirportId, arrivalAirportId, airlineId);

            return result.stream()
                    .map(Schedule::convertToResponse)
                    .collect(Collectors.toList());

        } else {
            List<Schedule> result = scheduleRepository.searchSchedules(departureTime, departureTimeStart, departureTimeEnd,
                    departureAirportId, arrivalAirportId, airlineId, className);

            return result.stream()
                    .map(Schedule::convertToResponse)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ScheduleResponseDTO> getRandomSchedules(int count) {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDTO> responseDTOs = new ArrayList<>();

        Random random = new Random();
        Set<Integer> selectedIndexes = new HashSet<>();

        int maxIndex = schedules.size() - 1;
        int selectedCount = 0;

        while (selectedCount < count && selectedCount <= maxIndex) {
            int randomIndex = random.nextInt(maxIndex + 1);
            if (!selectedIndexes.contains(randomIndex)) {
                Schedule schedule = schedules.get(randomIndex);
                ScheduleResponseDTO responseDTO = schedule.convertToResponse();
                responseDTOs.add(responseDTO);

                selectedIndexes.add(randomIndex);
                selectedCount++;
            }
        }
        return responseDTOs;
    }

    @Override
    public ScheduleResponseDTO getScheduleById(int id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return schedule.get().convertToResponse();
        }
        throw new RuntimeException("Data tidak ditemukan");

    }

    // @Override
    // public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO scheduleRequest) {
    // var airline = airlineRepository.findById(scheduleRequest.getAirlineId())
    // .orElseThrow(() -> new RuntimeException("Airline not found"));
    // var departureAirport =
    // airportRepository.findById(scheduleRequest.getDepartureAirportId())
    // .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
    // var arrivalAirport =
    // airportRepository.findById(scheduleRequest.getArrivalAirportId())
    // .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
    // var kelas = classRepository.findById(scheduleRequest.getClassId())
    // .orElseThrow(() -> new RuntimeException("Class not found"));
    // var schedule = scheduleRequest.toSchedule(departureAirport, arrivalAirport,
    // airline);
    // var result = scheduleRepository.save(schedule);
    // return result.convertToResponse();
    // }

    @Override
    public void deleteScheduleById(int id) {
        Optional<Schedule> result = scheduleRepository.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Data tidak ditemukan");
        }
        scheduleRepository.delete(result.get());
    }

}
