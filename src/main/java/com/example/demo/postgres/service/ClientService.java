package com.example.demo.postgres.service;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.Filter;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);

    List<ClientDto> getClients(Filter filter);
}
