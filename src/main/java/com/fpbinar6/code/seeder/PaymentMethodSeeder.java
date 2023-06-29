package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Order(4)
public class PaymentMethodSeeder implements CommandLineRunner {
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodSeeder(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedPaymentMethodData();
    }

    private void seedPaymentMethodData() throws IOException {
        // Define the relative path to the JSON file
        String jsonFilePath = "data/payment_method.json";

        // Read the JSON file from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

        if (inputStream == null) {
            throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
        }

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of PaymentMethod objects
        List<PaymentMethod> paymentMethods = objectMapper.readValue(inputStream, new TypeReference<List<PaymentMethod>>() {});

        // Upsert the payment methods to the database
        for (PaymentMethod paymentMethod : paymentMethods) {
            PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(paymentMethod.getPaymentMethodId()).orElse(null);
            if (existingPaymentMethod != null) {
                // Payment method already exists, update its fields
                existingPaymentMethod.setName(paymentMethod.getName());
                
                paymentMethodRepository.save(existingPaymentMethod);
            } else {
                // Payment method does not exist, save it as a new entry
                paymentMethodRepository.save(paymentMethod);
            }
        }

        inputStream.close();
    }
}
