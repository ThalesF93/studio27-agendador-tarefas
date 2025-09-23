package io.github.thalesf93.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServicesDTO(
        UUID id,

        @NotBlank(message = "Necessário informar o tipo de serviço")
        String serviceType,

        @NotNull(message = "Informe a data agendada")
        LocalDateTime eventDate,


        BigDecimal price
) {
}
