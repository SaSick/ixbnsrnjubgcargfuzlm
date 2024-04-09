package com.example.demo.mongo.repository;


import com.example.demo.mongo.model.Client;

import java.util.List;

public interface CustomClientRepository {

    List<Client> findAllClients(Integer limitParam, Integer offsetParam);

    Client findByIdOrPhoneNumberOrSecondPhoneNumber(Long id, String phoneNumber, String secondPhoneNumber);
}
