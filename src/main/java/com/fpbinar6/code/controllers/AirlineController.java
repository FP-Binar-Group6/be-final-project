package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.Airline;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpbinar6.code.services.AirlineService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AirlineController {
    
    final AirlineService airlineService;

    @GetMapping("/airline")
    public ResponseEntity<Object> getAllAirline() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airlineService.getAllAirline());
    }

    @GetMapping("/airline/{id}")
    public ResponseEntity<Object> getAirlineById(@PathVariable("id") int id) {
        try {
            var airline = airlineService.getAirlineById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, airline);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/airline/{id}")
    public ResponseEntity<Object> deteletAirlineById(@PathVariable("id") int id){

        try {
            airlineService.deleteAirlineById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/airline")
    public ResponseEntity<Object> updateAirline(@RequestBody Airline airline){
        try {
            airlineService.updateAirline(airline);
            return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, airline);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_EDIT_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/airline")
    public ResponseEntity<Object> saveAirline(@RequestBody Airline airline){
        try {
            airlineService.saveAirline(airline);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, airline);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
