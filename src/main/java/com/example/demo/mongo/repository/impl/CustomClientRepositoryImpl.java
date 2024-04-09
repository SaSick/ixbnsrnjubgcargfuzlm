package com.example.demo.mongo.repository.impl;

import com.example.demo.mongo.model.Client;
import com.example.demo.mongo.repository.CustomClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomClientRepositoryImpl implements CustomClientRepository {

    private final MongoTemplate mongoTemplate;


    @Override
    public List<Client> findAllClients(Integer limitParam, Integer skipParam) {
        Query query = new Query()
                .with(Sort.by(Sort.Order.asc("id")));

        if(limitParam != null){
            query.limit(limitParam);
        }
        if(skipParam != null){
            query.skip(skipParam);
        }

        return mongoTemplate.find(query, Client.class);
    }

    @Override
    public Client findByIdOrPhoneNumberOrSecondPhoneNumber(Long id, String phoneNumber, String secondPhoneNumber) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        List<Criteria> orCriteriaList = new ArrayList<>();

        if (id != null) {
            orCriteriaList.add(Criteria.where("_id").is(id));
        }

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            orCriteriaList.add(Criteria.where("phoneNumber").is(phoneNumber));
        }

        if (secondPhoneNumber != null && !secondPhoneNumber.isEmpty()) {
            orCriteriaList.add(Criteria.where("secondPhoneNumber").is(secondPhoneNumber));
        }

        criteria.orOperator(orCriteriaList.toArray(new Criteria[0]));
        query.addCriteria(criteria);

        return mongoTemplate.findOne(query, Client.class);
    }

}
