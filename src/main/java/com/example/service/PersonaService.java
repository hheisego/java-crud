package com.example.service;

import com.example.model.Persona;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonaService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Persona> findAll() {
        return personRepository.findAll();
    }

    public Optional<Persona> findById(String id) {
        return personRepository.findById(id);
    }

    public Persona save(Persona person) {
        return personRepository.save(person);
    }


    public void deleteById(String id) {
        personRepository.deleteById(id);
    }

}
