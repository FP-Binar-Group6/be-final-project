package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.Airline;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.services.AirlineService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airline")
public class AirlineController {
    
    final AirlineService airlineService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllAirline() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airlineService.getAllAirline());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAirlineById(@PathVariable("id") int id) {
        var airline = airlineService.getAirlineById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airline);
    }
}
