package com.example.demo.mongo.service;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.ClientParams;
import com.example.demo.core.dto.Filter;

import java.util.List;

public interface ClientService {

    ClientDto addClient(ClientDto clientDto);

    List<ClientDto> getAllClients(Filter filter);

    ClientDto getClient(ClientParams clientParams);

    ClientDto updateClient(ClientParams clientParams, ClientDto request);

    String deleteClient(ClientParams clientParams);
}
