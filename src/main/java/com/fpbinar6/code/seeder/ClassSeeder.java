package com.fpbinar6.code.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpbinar6.code.models.Class;
import com.fpbinar6.code.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Component
public class ClassSeeder implements CommandLineRunner {
    private final ClassRepository classRepository;

    @Autowired
    public ClassSeeder(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedClassData();
    }

    private void seedClassData() throws IOException {
    // Define the relative path to the JSON file
    String jsonFilePath = "data/class.json";

    // Read the JSON file from the classpath
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

    if (inputStream == null) {
        throw new FileNotFoundException("JSON file not found: " + jsonFilePath);
    }

    // Create an ObjectMapper instance
    ObjectMapper objectMapper = new ObjectMapper();

    // Map the JSON content to a list of Class objects
    List<Class> classes = objectMapper.readValue(inputStream, new TypeReference<List<Class>>() {});

    // Save the classes to the database
    classRepository.saveAll(classes);

    inputStream.close();
}

}
