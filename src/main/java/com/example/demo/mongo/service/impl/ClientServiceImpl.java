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
        Client clientFromDB = findByIdOrPhoneNumberOrSecondPhoneNumber(clientDto.getId(), clientDto.getPhoneNumber(),
                clientDto.getSecondPhoneNumber());

        if(clientFromDB != null){
            throw new APIException("Client already exists!");
        }

        clientFromDB = mapper.map(clientDto, Client.class);

        clientRepository.save(clientFromDB);

        return mapper.map(clientFromDB, ClientDto.class);
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
        Client clientFromDB = findByIdOrPhoneNumberOrSecondPhoneNumber(clientParams.getId(),
                clientParams.getPhoneNumber(), clientParams.getSecondPhoneNumber());

        if(clientFromDB == null){
            throw new ClientNotFoundException("Client not found!");
        }

        return mapper.map(clientFromDB, ClientDto.class);
    }

    @Override
    public ClientDto updateClient(ClientParams clientParams, ClientDto request) {
        Client clientFromDB = findByIdOrPhoneNumberOrSecondPhoneNumber(clientParams.getId(),
                clientParams.getPhoneNumber(), clientParams.getSecondPhoneNumber());

        if(clientFromDB == null){
            throw new ClientNotFoundException("Client not found!");
        }

        List<Client> existingClients = clientRepository.findByPhoneNumberOrSecondPhoneNumber(
                request.getPhoneNumber(), request.getSecondPhoneNumber());

        existingClients.removeIf(c -> c.getId().equals(clientFromDB.getId()));

        if (!existingClients.isEmpty()) {
            throw new APIException("Client with provided first phone number or second phone number already exists!");
        }

        Client existingClientWithSameId = clientRepository.findById(request.getId()).orElse(null);
        if (existingClientWithSameId != null && !existingClientWithSameId.getId().equals(clientFromDB.getId())) {
            throw new APIException("Client with provided id already exists!");
        }

        mapper.map(request, clientFromDB);

        clientRepository.save(clientFromDB);

        return mapper.map(clientFromDB, ClientDto.class);

    }

    @Transactional
    @Override
    public String deleteClient(ClientParams clientParams) {
        Client client = findByIdOrPhoneNumberOrSecondPhoneNumber(clientParams.getId(),
                clientParams.getPhoneNumber(), clientParams.getSecondPhoneNumber());

        if (client == null){
            throw new ClientNotFoundException("Client not found!");
        }

        clientRepository.delete(client);
        return "Client is successfully deleted!";
    }

    private Client findByIdOrPhoneNumberOrSecondPhoneNumber(Long id, String PhoneNumber, String SecondPhoneNumber){
        return customRepository.findByIdOrPhoneNumberOrSecondPhoneNumber(id, PhoneNumber, SecondPhoneNumber);
    }
}
