package com.faculdade.gerenciamento_pessoas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;

    String cpf;

    int age;

}
