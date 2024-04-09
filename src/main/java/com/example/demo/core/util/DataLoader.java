package com.example.demo.core.util;

import com.example.demo.mongo.model.Client;
import com.example.demo.mongo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args){
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        try{
            InputStream stream = new ClassPathResource("db/data/clients-data.csv").getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String headerLine = reader.readLine();

            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                Long id = Long.parseLong(data[0].trim());
                String name = data[1].trim();
                String phoneNumber = data[2].trim();
                String secondPhoneNumber = data[3].trim();
                LocalDate birthDate = LocalDate.parse(data[4].trim());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createdAt = LocalDateTime.parse(data[5].trim(), formatter);
                Client client = new Client(id, name, phoneNumber, secondPhoneNumber, birthDate, createdAt);
                clientRepository.save(client);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
