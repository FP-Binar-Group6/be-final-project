package com.fpbinar6.code.controllers;

import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.services.PaymentMethodService;
import com.fpbinar6.code.utils.Constants;
import com.fpbinar6.code.utils.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymentmethod")
@RequiredArgsConstructor
public class PaymentMethodController {
    final PaymentMethodService paymentMethodService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllPaymentMethod() {
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, paymentMethodService.getAllPaymentMethod());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentMethodById(@PathVariable("id") int id) {
        try {
            var paymentMethod = paymentMethodService.getPaymentMethodById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, paymentMethod);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_RETRIEVE_MSG, HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaymentMethodById(@PathVariable("id") int id){

        try {
            paymentMethodService.deletePaymentMethodById(id);
            return ResponseHandler.generateResponse(Constants.SUCCESS_DELETE_MSG, HttpStatus.OK, id);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_DELETE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping("/")
    public ResponseEntity<Object> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        try {
            paymentMethodService.updatePaymentMethod(paymentMethod);
            return ResponseHandler.generateResponse(Constants.SUCCESS_EDIT_MSG, HttpStatus.OK, paymentMethod);
            
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_EDIT_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> savePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        try {
            paymentMethodService.savePaymentMethod(paymentMethod);
            return ResponseHandler.generateResponse(Constants.SUCCESS_SAVE_MSG, HttpStatus.OK, paymentMethod);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(Constants.ERROR_SAVE_MSG, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
