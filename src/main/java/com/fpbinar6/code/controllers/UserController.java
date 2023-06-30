package com.fpbinar6.code.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.dto.UserUpdateDTO;
import com.fpbinar6.code.services.UserService;
import com.fpbinar6.code.utils.*;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    final UserService userService;
    
    @GetMapping("/user")
    public ResponseEntity<Object> getAllUser() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, userService.getAllUser());
    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, userService.updateUser(userUpdateDTO));
    }
    
}
