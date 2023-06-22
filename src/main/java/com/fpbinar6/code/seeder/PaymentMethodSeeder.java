package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.PaymentMethod;
import com.fpbinar6.code.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class PaymentMethodSeeder implements CommandLineRunner {
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodSeeder(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedClassData();
    }

    private void seedClassData() throws IOException {
        // Define the relative path to the JSON file
        String jsonFilePath = "data/payment_method.json";

        // Read the JSON file
        Path path = new ClassPathResource(jsonFilePath).getFile().toPath();
        String jsonContent = Files.readString(path);

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Map the JSON content to a list of Airline objects
        List<PaymentMethod> paymentMethods = objectMapper.readValue(jsonContent, new TypeReference<List<PaymentMethod>>() {});

        // Save the airlines to the database
        paymentMethodRepository.saveAll(paymentMethods);
    }
}
