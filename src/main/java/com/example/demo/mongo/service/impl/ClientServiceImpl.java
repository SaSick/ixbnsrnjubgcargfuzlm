package com.example.demo.mongo.service.impl;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.ClientParams;
import com.example.demo.core.dto.Filter;
import com.example.demo.core.exception.APIException;
import com.example.demo.core.exception.ClientNotFoundException;
import com.example.demo.mongo.model.Client;
import com.example.demo.mongo.repository.ClientRepository;
import com.example.demo.mongo.repository.CustomClientRepository;
import com.example.demo.mongo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "mongoService")
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ModelMapper mapper;
    private final ClientRepository clientRepository;
    private final CustomClientRepository customRepository;
    
    
    @Transactional
    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = findByIdOrPhoneNumberOrSecondPhoneNumber(clientDto.getId(), clientDto.getPhoneNumber(),
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
    public List<ClientDto> getAllClients(Filter filter) {
        Integer limit = (filter.getLimit() != null) ? filter.getLimit() : null;
        Integer offset = (filter.getOffset() != null) ? filter.getOffset() : null;

        List<Client> clients = customRepository.findAllClients(limit, offset);
        return clients.stream()
                .map(client -> mapper.map(client, ClientDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ClientDto getClient(ClientParams clientParams) {
        Client client = findByIdOrPhoneNumberOrSecondPhoneNumber(clientParams.getId(),
                clientParams.getPhoneNumber(), clientParams.getSecondPhoneNumber());

        if(client == null){
            throw new ClientNotFoundException("Client not found!");
        }

        return mapper.map(client, ClientDto.class);
    }

    private Client findByIdOrPhoneNumberOrSecondPhoneNumber(Long id, String PhoneNumber, String SecondPhoneNumber){
        return customRepository.findByIdOrPhoneNumberOrSecondPhoneNumber(id, PhoneNumber, SecondPhoneNumber);
    }
}
