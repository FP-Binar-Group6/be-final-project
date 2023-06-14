package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.Airline;
import com.fpbinar6.code.models.Class;
import com.fpbinar6.code.services.AirlineService;
import com.fpbinar6.code.services.ClassService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {

    final ClassService classService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllClass() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, classService.getAllClass());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClassById(@PathVariable("id") int id) {
        var kelas = classService.getClassById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, kelas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deteletClassById(@PathVariable("id") int id){
        classService.deleteClassById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, id);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateClass(@RequestBody Class kelas) {
        classService.updateClass(kelas);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, kelas);
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveClass(@RequestBody Class kelas){
        classService.saveClass(kelas);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, kelas);
    }

}
