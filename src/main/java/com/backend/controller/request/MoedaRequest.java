package com.backend.controller.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MoedaRequest(
        @NotBlank(message = "O nome da moeda não pode estar nulo/branco")
        @Size(max = 50, message = "O nome da moeda não pode ter mais que 50 caracteres")
        String nomeMoeda,

        @NotNull(message = "A taxa de câmbio não pode estar nula")
        @Positive(message = "A taxa de câmbio deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "A taxa de câmbio deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
        BigDecimal taxaCambio
) {}
