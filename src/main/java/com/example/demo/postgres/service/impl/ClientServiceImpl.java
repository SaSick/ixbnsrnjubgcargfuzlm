package com.example.demo.postgres.service.impl;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.Filter;
import com.example.demo.core.exception.APIException;
import com.example.demo.postgres.model.Client;
import com.example.demo.postgres.repository.ClientRepository;
import com.example.demo.postgres.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ModelMapper mapper;
    private final ClientRepository clientRepository;


    @Transactional
    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientRepository
                .findByPhoneNumberAndSecondPhoneNumber(clientDto.getPhoneNumber(),
                        clientDto.getSecondPhoneNumber());

        if(client != null){
            throw new APIException("Client already exists!");
        }

        client = mapper.map(clientDto, Client.class);

        clientRepository.save(client);

        return mapper.map(client, ClientDto.class);
    }

    @Transactional
    @Override
    public List<ClientDto> getClients(Filter filter) {
        Integer limit = (filter.getLimit() != null) ? filter.getLimit() : null;
        Integer offset = (filter.getOffset() != null) ? filter.getOffset() : null;

        List<Client> clients = clientRepository.findAll(limit, offset);
        return clients
                .stream().map(client -> mapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }
}
