package com.backend.controller.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CriarTransacaoRequest(
        @NotBlank(message = "O nome do item não pode estar nulo/branco")
        @Size(max = 50, message = "O nome do item não pode ter mais que 50 caracteres")
        String nomeItem,

        @NotNull(message = "A quantidade não pode ser nulo")
        @Positive(message = "A quantidade valor deve ser maior que zero")
        BigDecimal quantidade,

        @NotBlank(message = "A moeda de origem não pode estar nula/branca")
        @Size(max = 50, message = "A moeda de origem não pode ter mais que 50 caracteres")
        String moedaOrigem,

        @NotBlank(message = "A moeda de destino não pode estar nula/branca")
        @Size(max = 50, message = "A moeda de destino não pode ter mais que 50 caracteres")
        String moedaDestino
) {
}
