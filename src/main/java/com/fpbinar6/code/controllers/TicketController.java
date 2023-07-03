package com.fpbinar6.code.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpbinar6.code.models.dto.TicketBookingResponseDTO;
import com.fpbinar6.code.models.dto.TicketRequestDTO;
import com.fpbinar6.code.models.dto.TicketResponseDTO;
import com.fpbinar6.code.services.TicketService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TicketController {

    final TicketService ticketService;

    @GetMapping("/ticket")
    public ResponseEntity<Object> getTicketByPaymentId(@PathVariable("id") int id) {
        try {
            var ticket = ticketService.getTicketByPaymentId(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, ticket);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable("id") Long id) {
        try {
            var ticket = ticketService.getTicketById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, ticket);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/ticket")
    public ResponseEntity<Object> saveTicket(@RequestBody TicketRequestDTO ticketRequest) {
        try {
            var savedTicket = ticketService.saveTicket(ticketRequest);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, savedTicket);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/tickets")
    public ResponseEntity<Object> saveAllTickets(@RequestBody List<TicketRequestDTO> ticketRequests) {
        try {
            var savedTickets = ticketService.saveAllTickets(ticketRequests);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, savedTickets);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Object> deleteTicketById(@PathVariable("id") Long id) {
        try {
            ticketService.deleteTicketById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
