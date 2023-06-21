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
