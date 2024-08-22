package com.example.controller;

import com.example.model.Persona;
import com.example.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonaService personService;

    @Autowired
    public PersonController(PersonaService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Persona> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonById(@PathVariable String id) {
        Optional<Persona> person = personService.findById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Persona createPerson(@RequestBody Persona person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePerson(@PathVariable String id, @RequestBody Persona personDetails) {
        Optional<Persona> person = personService.findById(id);
        if (person.isPresent()) {
            Persona updatedPerson = person.get();
            updatedPerson.setName(personDetails.getName());
            updatedPerson.setEmail(personDetails.getEmail());
            return new ResponseEntity<>(personService.save(updatedPerson), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable String id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}