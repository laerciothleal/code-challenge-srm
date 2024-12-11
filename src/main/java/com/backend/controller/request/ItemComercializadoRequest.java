package com.backend.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemComercializadoRequest(
        @NotBlank(message = "O nome do item não pode estar nulo/branco")
        String nomeItem,

        @NotBlank(message = "O reino de origem não pode estar nulo/branco")
        String reinoOrigem,

        @NotNull(message = "O preço base não pode estar nulo")
        @Positive(message = "O preço base deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O preço base deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
        BigDecimal precoBase
) {}
