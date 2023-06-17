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
        var paymentMethod = paymentMethodService.getPaymentMethodById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, paymentMethod);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaymentMethodById(@PathVariable("id") int id){
        paymentMethodService.deletePaymentMethodById(id);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, id);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.updatePaymentMethod(paymentMethod);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, paymentMethod);
    }

    @PostMapping("/")
    public ResponseEntity<Object> savePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        paymentMethodService.savePaymentMethod(paymentMethod);
        return ResponseHandler.generateResponse(Constants.SUCCESS_RETRIEVE_MSG, HttpStatus.OK, paymentMethod);
    }
}
