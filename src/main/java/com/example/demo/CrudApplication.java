package com.example.demo;

import com.example.demo.mongo.model.Client;
import com.example.demo.mongo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudApplication implements CommandLineRunner {

	private final ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		clientRepository.save(new Client(1L, "Test1","1111","1111",LocalDate.of(2000, 5, 17), LocalDateTime.now()));
		clientRepository.save(new Client(2L, "Test2","2222","2222", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(3L, "Test3","3333","3333", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(4L, "Test4","4444","4444", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(5L, "Test5","5555","5555", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(6L, "Test6","6666","6666", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(7L, "Test7","7777","7777", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(8L, "Test8","8888","8888", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(9L, "Test9","9999","9999", LocalDate.now(), LocalDateTime.now()));
		clientRepository.save(new Client(10L, "Test10","1010","1010", LocalDate.now(), LocalDateTime.now()));
	}
}
