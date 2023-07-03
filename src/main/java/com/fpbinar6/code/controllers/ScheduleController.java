package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;
import com.fpbinar6.code.repository.ScheduleRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpbinar6.code.services.ScheduleService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    final ScheduleService scheduleService;
    final ScheduleRepository scheduleRepository;

    @GetMapping("/schedule")
    public ResponseEntity<Object> getAllSchedule() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK,
                scheduleService.getAllSchedule());
    }

    @GetMapping("/schedule/search")
    public ResponseEntity<Object> searchSchedules(
            @RequestParam(value = "departureDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
            @RequestParam(value = "departureAirportId", required = false) Integer departureAirportId,
            @RequestParam(value = "arrivalAirportId", required = false) Integer arrivalAirportId,
            @RequestParam(value = "airlineId", required = false) Integer airlineId) {
        Timestamp departureTime = null;
        if (departureDate != null) {
            departureTime = Timestamp.valueOf(departureDate.atStartOfDay());
        }

        List<ScheduleResponseDTO> schedules = scheduleService.searchSchedules(departureTime, departureAirportId,
                arrivalAirportId, airlineId);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedules);
    }

    @GetMapping("/schedule/random")
    public ResponseEntity<Object> getRandomSchedules() {
        List<ScheduleResponseDTO> randomSchedules = scheduleService.getRandomSchedules(5);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, randomSchedules);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Object> getScheduleById(@PathVariable("id") int id) {
        try {
            var schedule = scheduleService.getScheduleById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedule);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Object> deleteScheduleById(@PathVariable("id") int id) {
        try {
            scheduleService.deleteScheduleById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // @PostMapping("/schedule")
    // public ResponseEntity<Object> saveSchedule(@RequestBody ScheduleRequestDTO scheduleRequest) {
    //     try {
    //         scheduleService.saveSchedule(scheduleRequest);
    //         return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, scheduleRequest);

    //     } catch (Exception e) {
    //         return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
    //     }
    // }

}
