package com.backend.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MoedaRequest(
        @NotBlank(message = "O nome da moeda não pode estar nulo/branco")
        String nomeMoeda,

        @NotNull(message = "A taxa de câmbio não pode estar nula")
        @Positive(message = "A taxa de câmbio deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "A taxa de câmbio deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
        BigDecimal taxaCambio
) {}
