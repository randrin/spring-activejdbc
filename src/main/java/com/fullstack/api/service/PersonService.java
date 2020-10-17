package com.fullstack.api.service;

import com.fullstack.api.model.InputRequest;
import com.fullstack.api.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    public String insertPerson(InputRequest request) {
        Person person = new Person();
        person.set("id", request.getId());
        person.set("firstName", request.getFirstName());
        person.set("lastName", request.getLastName());
        person.insert();
        return "Person inserted: " +person.insert();
    }

    public List<Person> getPersons() {
        return Person.findAll();
    }

    public List<Person> getPersonsByFirstName(String firstName) {
        List<Person> persons = Person.where("firstName=?", firstName);
        System.out.println("List Persons with firstName: " +persons);
        return persons;
    }

    public String deletePerson(String firstName) {
        int personId = Person.delete("firstName=?", firstName);
        return "Person deleted: " +personId;
    }

    public String deleteAllPersons() {
        int allPersons = Person.deleteAll();
        return "Deleted all persons: " +allPersons;
    }
}
