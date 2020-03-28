package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService ps;

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id){
        return new ResponseEntity<>(ps.findById(id), HttpStatus.OK);
    }

    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> showAllPeople(){
        return new ResponseEntity<>(ps.showAll(), HttpStatus.OK);
    }








}
