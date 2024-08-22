package com.example.repository;
import com.example.model.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Persona, String> {

    
}