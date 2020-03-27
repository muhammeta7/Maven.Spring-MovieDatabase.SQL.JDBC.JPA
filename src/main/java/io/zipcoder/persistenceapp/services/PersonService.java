package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    PersonRepository repo;

    @Autowired
    public PersonService(PersonRepository repo){
        this.repo = repo;
    }



}
