package com.faculdade.gerenciamento_pessoas.service;

import com.faculdade.gerenciamento_pessoas.dto.PersonDto;
import com.faculdade.gerenciamento_pessoas.model.Person;
import com.faculdade.gerenciamento_pessoas.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> createPerson(PersonDto personDto){
        if (!CPFExists(personDto.cpf())){
            Person person = new Person();
            BeanUtils.copyProperties(personDto, person);
            return Optional.of(personRepository.save(person));
        }
        return Optional.empty();

    }

    public boolean CPFExists(String cpf){
        return personRepository.findByCpf(cpf).isPresent();
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
    public Optional<Person> findById(UUID id){
        return personRepository.findById(id);
    }

    public boolean deletePersonById(UUID id){
        Optional<Person> foundPerson = this.findById(id);
        if (!foundPerson.isEmpty()){
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Person> updatePersonById(PersonDto personDto,UUID id){
        Optional<Person> foundPerson = this.findById(id);
        if(!foundPerson.isEmpty()){
            Person person = foundPerson.get();
            BeanUtils.copyProperties(personDto, person);
            return Optional.of(personRepository.save(person));
        }
        return Optional.empty();
    }
}
