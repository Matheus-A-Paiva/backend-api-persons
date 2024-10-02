package com.faculdade.gerenciamento_pessoas.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PersonDto(
        @NotEmpty
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50.")
        String name,

        @NotEmpty
        @Size(min = 11, max = 14, message = "CPF must be between 11 and 14.")
        String cpf,

        @NotNull(message = "Age must not be null.")
        Integer age) {
}
