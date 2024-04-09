package com.example.demo.mongo.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Document(collection = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    private Long id;
    private String username;
    private String phoneNumber;
    private String secondPhoneNumber;
    private LocalDate birthDate;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(username, client.username) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(secondPhoneNumber, client.secondPhoneNumber) && Objects.equals(birthDate, client.birthDate) && Objects.equals(createdAt, client.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, phoneNumber, secondPhoneNumber, birthDate, createdAt);
    }


}
