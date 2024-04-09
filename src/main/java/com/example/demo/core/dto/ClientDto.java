package com.example.demo.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private String secondPhoneNumber;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
}
