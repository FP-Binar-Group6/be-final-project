package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.Class;
import com.fpbinar6.code.services.ClassService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassController {

    final ClassService classService;

    @GetMapping("/class")
    public ResponseEntity<Object> getAllClass() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, classService.getAllClass());
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<Object> getClassById(@PathVariable("id") int id) {

        try {
            var kelas = classService.getClassById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, kelas);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/class/{id}")
    public ResponseEntity<Object> deleteClassById(@PathVariable("id") int id){

        try {
            classService.deleteClassById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/class")
    public ResponseEntity<Object> updateClass(@RequestBody Class kelas) {

        try {
            classService.updateClass(kelas);
            return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, kelas);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_EDIT_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/class")
    public ResponseEntity<Object> saveClass(@RequestBody Class kelas){
        try {
            classService.saveClass(kelas);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, kelas);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
