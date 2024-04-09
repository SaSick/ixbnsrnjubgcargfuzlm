package com.example.demo.postgres.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {
    @Id
    @SequenceGenerator(
            name = "clients_seq",
            sequenceName = "clients_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_seq")
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                '}';
    }
}
