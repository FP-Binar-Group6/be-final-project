package com.fpbinar6.code.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.services.TicketService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    final TicketService ticketService;
    
    @GetMapping("/")
    public ResponseEntity<Object> getTicketByPaymentId(@PathVariable("id") int id){
        try {
            var ticket = ticketService.getTicketByPaymentId(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, ticket);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable("id") int id){
        try {
            var ticket = ticketService.getTicketById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, ticket);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveTicket(@RequestBody TicketRequestDTO ticketRequest){
        try {
            ticketService.saveTicket(ticketRequest);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, ticketRequest);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicketById(@PathVariable("id") int id){
        try {
            ticketService.deleteTicketById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
