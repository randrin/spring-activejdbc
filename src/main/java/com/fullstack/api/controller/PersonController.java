package com.fullstack.api.controller;

import com.fullstack.api.model.InputRequest;
import com.fullstack.api.model.Person;
import com.fullstack.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

     @Autowired
    private PersonService personService;

    @PostMapping("/savePerson")
    public String savePerson(@RequestBody InputRequest request) {
        return personService.insertPerson(request);
    }

    @GetMapping("/getPersons")
    public List<Person> getPerson() {
        return personService.getPersons();
    }

    @GetMapping("/getPersonsByFirstName/{firstName}")
    public List<Person> getPersonsByFirstName(@PathVariable String firstName) {
        return personService.getPersonsByFirstName(firstName);
    }

    @DeleteMapping("/deletePerson/{firstName}")
    public String deletePerson(@PathVariable String firstName) {
        return personService.deletePerson(firstName);
    }

    @DeleteMapping("/deleteAllPersons")
    public String deleteAllPersons() {
        return personService.deleteAllPersons();
    }
}
