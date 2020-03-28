package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.jbdc.JdbcPersonServiceImpl;
import io.zipcoder.persistenceapp.models.Person;
import io.zipcoder.persistenceapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    JdbcPersonServiceImpl ps;

    public PersonController(JdbcPersonServiceImpl service){
        this.ps = service;
    }

    // GET /people/{id} -- get the person with the specified ID
    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id){
        return new ResponseEntity<>(ps.findById(id), HttpStatus.OK);
    }

    // GET /people -- get all people in the database
    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> showAllPeople(){
        return new ResponseEntity<>(ps.showAll(), HttpStatus.OK);
    }

    // GET /people/surname/{lastName} -- Find all people with a particular last name
    @GetMapping("/people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> getAllBySurname(@PathVariable String lastName){
        return new ResponseEntity<>(ps.findAllWithLastName(lastName), HttpStatus.OK);
    }

    // GET /people/firstName/{firstName} -- Find all people with a particular first name
    @GetMapping("/people/firstname/{firstName}")
    public ResponseEntity<Iterable<Person>> getAllByFirstName(@PathVariable String firstName){
        return new ResponseEntity<>(ps.findAllWithFirstName(firstName), HttpStatus.OK);
    }

    // GET /people/birthday/{birthday} -- Find all people with a particular birthday
    @GetMapping("/people/birthday/{bday}")
    public ResponseEntity<Iterable<Person>> getAllByBirthday(@PathVariable String bday){
        return new ResponseEntity<>(ps.findAllWithBirthday(bday), HttpStatus.OK);
    }

    // TODO: Returns only one person
    // GET /people/reverselookup/{mobileNumber} -- find all people with the specified mobile number
    @GetMapping("/people/reverselookup/{mobile}")
    public ResponseEntity<Iterable<Person>> getAllByMobile(@PathVariable String mobile){
        return new ResponseEntity<>(ps.findAllWithMobile(mobile), HttpStatus.OK);
    }

    // POST /people -- create a person
    @PostMapping(value = "/people/new")
    public ResponseEntity<?> addPerson(@RequestBody Person p){
        ps.createPerson(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // DELETE /people/{id} -- Delete the person with the specified ID
    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return new ResponseEntity<>(ps.deletePerson(id), HttpStatus.NOT_FOUND);
    }

    // PUT /people/{id} -- update person with id. 404 error if that person doesn't exist yet
    @PutMapping(value = "/people/{id}")
    public void update(@RequestBody Person person, @PathVariable Integer id) {
        ps.updatePerson(id,person);
    }

    // GET /people/surname -- Get the result of the surname report above
    @GetMapping("/people/surname")
    public ResponseEntity<Map<String, ArrayList<Person>>> mapSurnames(){
        return new ResponseEntity<>(ps.getSurnameMap(), HttpStatus.OK);
    }

    // GET /people/firstname/stats -- Get the report of first name frequencies
    @GetMapping("/people/firstname/stats")
    public ResponseEntity<Map<String, Integer>> mapFirstNames(){
        return new ResponseEntity<>(ps.getFirstNameMap(), HttpStatus.OK);
    }

}
