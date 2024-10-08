package com.faculdade.gerenciamento_pessoas.repository;

import com.faculdade.gerenciamento_pessoas.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    public Optional<Person> findByCpf(String cpf);
}
