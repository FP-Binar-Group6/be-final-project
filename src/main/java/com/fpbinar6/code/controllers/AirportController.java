package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.Airport;
import com.fpbinar6.code.services.AirportService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {
    final AirportService airportService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllAirport() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airportService.getAllAirport());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAirportById(@PathVariable("id") int id) {
        var airport = airportService.getAirportById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAirportById(@PathVariable("id") int id){
        airportService.deleteAirportById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, id);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateAirport(@RequestBody Airport airport) {
        airportService.updateAirport(airport);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airport);
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveAirport(@RequestBody Airport airport){
        airportService.saveAirport(airport);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airport);
    }
}
