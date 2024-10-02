package com.faculdade.gerenciamento_pessoas.controller;

import com.faculdade.gerenciamento_pessoas.dto.PersonDto;
import com.faculdade.gerenciamento_pessoas.model.Person;
import com.faculdade.gerenciamento_pessoas.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Object> createPerson(@Valid @RequestBody PersonDto personDto){

        Optional<Person> savedPerson = personService.createPerson(personDto);
        if(!savedPerson.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF already exists.");

    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> allPersons = personService.getAllPersons();

        return ResponseEntity.status(HttpStatus.OK).body(allPersons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonById(@PathVariable UUID id){
        Optional<Person> foundPerson = personService.findById(id);
        if(foundPerson.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundPerson.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable UUID id){
        boolean isDeleted = personService.deletePersonById(id);
        if(!isDeleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Person deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePersonById(@Valid @RequestBody PersonDto personDto, @PathVariable UUID id){
        Optional<Person> updatedPerson = personService.updatePersonById(personDto, id);
        if(updatedPerson.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }


}
