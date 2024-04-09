package com.example.demo.mongo.repository;

import com.example.demo.mongo.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {
    List<Client> findByPhoneNumberOrSecondPhoneNumber(String phoneNumber, String secondPhoneNumber);
}
