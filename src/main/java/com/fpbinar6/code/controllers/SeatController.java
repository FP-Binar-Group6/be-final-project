package com.fpbinar6.code.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.dto.SeatRequestDTO;
import com.fpbinar6.code.services.SeatService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/seat")
@RequiredArgsConstructor
public class SeatController {
    final SeatService seatService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllSeat() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, seatService.getAllSeat());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeatById(@PathVariable("id") int id) {
        var seat = seatService.getSeatById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, seat);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<Object> getSeatByScheduleId(@PathVariable("scheduleId") int scheduleId) {
        var seat = seatService.getSeatByScheduleId(scheduleId);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, seat);
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveSeat(@RequestBody SeatRequestDTO seatRequest){
        seatService.saveSeat(seatRequest);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, seatRequest);
    }
}
