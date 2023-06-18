package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.dto.ScheduleRequestDTO;

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

    @GetMapping("/")
    public ResponseEntity<Object> getAllSchedule() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, scheduleService.getAllSchedule());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getScheduleById(@PathVariable("id") int id) {
        var schedule = scheduleService.getScheduleById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteScheduleById(@PathVariable("id") int id){
        scheduleService.deleteScheduleById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveSchedule(@RequestBody ScheduleRequestDTO scheduleRequest){
        scheduleService.saveSchedule(scheduleRequest);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, scheduleRequest);
    }

}
