package com.example.demo.mongo.controller;

import com.example.demo.mongo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class ClientController {
    private final ClientService clientService;


}
