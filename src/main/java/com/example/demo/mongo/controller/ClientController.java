package com.example.demo.mongo.controller;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.ClientParams;
import com.example.demo.core.dto.Filter;
import com.example.demo.mongo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "mongoController")
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto addClient(
            @RequestBody ClientDto clientDto
    ){
        return clientService.addClient(clientDto);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients(
            @SpringQueryMap Filter filter
    ){
        return clientService.getAllClients(filter);
    }

    @GetMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClient(
            @SpringQueryMap ClientParams clientParams
    ){
        return clientService.getClient(clientParams);
    }

    @PutMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto updateClient(
            @SpringQueryMap ClientParams clientParams,
            @RequestBody ClientDto request
    ){
        return clientService.updateClient(clientParams, request);
    }
}
