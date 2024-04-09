package com.example.demo.postgres.repository;

import com.example.demo.postgres.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhoneNumberAndSecondPhoneNumber(String phoneNumber, String secondPhoneNumber);

    Client findClientByIdOrPhoneNumberOrSecondPhoneNumber(Long id, String phoneNumber, String secondPhoneNumber);

    @Query(value = "SELECT c.* FROM clients c ORDER BY c.id"
            + " LIMIT :limitParam"
            + " OFFSET :offsetParam",
            nativeQuery = true)
    List<Client> findAll(
            @Param("limitParam") Integer limitParam,
            @Param("offsetParam") Integer offsetParam
    );


}
