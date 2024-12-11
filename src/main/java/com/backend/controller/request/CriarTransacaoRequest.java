package com.backend.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CriarTransacaoRequest(
        @NotBlank(message = "O nome do item não pode estar nulo/branco")
        @Max(value = 50, message = "O nome do item não pode ter mais que 50 caracteres")
        String nomeItem,

        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O valor deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O valor deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
        BigDecimal valor,

        @NotBlank(message = "A moeda de origem não pode estar nula/branca")
        @Max(value = 50, message = "A moeda de origem não pode ter mais que 50 caracteres")
        String moedaOrigem,

        @NotBlank(message = "A moeda de destino não pode estar nula/branca")
        @Max(value = 50, message = "A moeda de destino não pode ter mais que 50 caracteres")
        String moedaDestino
) {
}
