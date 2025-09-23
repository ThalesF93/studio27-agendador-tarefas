package io.github.thalesf93.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record CostumersDTO(
        UUID id,

        @NotBlank(message = "Campo Obrigatório")
        String name,

        @NotNull(message = "Telefone Obrigatório")
        @JsonFormat(pattern = "11-111111111")
        String phone,

        @Email
        String email,

        @CPF
        String cpf,

        String address,

        UUID idEmployee
        ) {
}
