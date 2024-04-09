package com.example.demo.postgres.controller;

import com.example.demo.core.dto.ClientDto;
import com.example.demo.core.dto.ClientParams;
import com.example.demo.core.dto.Filter;
import com.example.demo.postgres.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
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
        return clientService.getClients(filter);
    }

    @GetMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClient(
            @SpringQueryMap ClientParams clientParams
    ){
        return clientService.getClient(clientParams);
    }
}
