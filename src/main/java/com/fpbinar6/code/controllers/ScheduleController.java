package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;
import com.fpbinar6.code.models.dto.ScheduleResponseDTO;
import com.fpbinar6.code.repository.ScheduleRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpbinar6.code.services.ScheduleService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {
    
    final ScheduleService scheduleService;
    final ScheduleRepository scheduleRepository;

    @GetMapping("/")
    public ResponseEntity<Object> getAllSchedule() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, scheduleService.getAllSchedule());
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchSchedules(
            @RequestParam(value = "departureTime", required = false) Timestamp departureTime,
            @RequestParam(value = "arrivalTime", required = false) Timestamp arrivalTime,
            @RequestParam(value = "departureAirportId", required = false) Integer departureAirportId,
            @RequestParam(value = "arrivalAirportId", required = false) Integer arrivalAirportId,
            @RequestParam(value = "airlineId", required = false) Integer airlineId
    ) {
        ScheduleRequestDTO scheduleRequest = ScheduleRequestDTO.builder()
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .departureAirportId(departureAirportId)
                .arrivalAirportId(arrivalAirportId)
                .airlineId(airlineId)
                .build();
        
        if (airlineId == null) {
            // List<ScheduleResponseDTO> schedulesWithoutAirlineId = scheduleService.searchSchedulesWithoutAirlineId(scheduleRequest);
            
            // return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedulesWithoutAirlineId);
        }

        List<ScheduleResponseDTO> schedules = scheduleService.searchSchedules(scheduleRequest);

        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getScheduleById(@PathVariable("id") int id) {
        try {
            var schedule = scheduleService.getScheduleById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedule);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteScheduleById(@PathVariable("id") int id){
        try {
            scheduleService.deleteScheduleById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveSchedule(@RequestBody ScheduleRequestDTO scheduleRequest){
        try {
            scheduleService.saveSchedule(scheduleRequest);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, scheduleRequest);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
