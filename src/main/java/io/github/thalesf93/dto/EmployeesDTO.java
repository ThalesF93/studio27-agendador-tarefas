package io.github.thalesf93.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

public record EmployeesDTO(
    UUID id,

    @NotBlank(message = "Campo Obrigatório")
    String name,

    @NotBlank(message = "E-mail Obrigatório")
    @Email
    String email,

    String login,

    @NotBlank(message = "Senha obrigatória")
    String password,

    @CPF
    @NotNull(message = "CPF obrigatório")
    String cpf,

    String address,

    List<String> roles) {
}
