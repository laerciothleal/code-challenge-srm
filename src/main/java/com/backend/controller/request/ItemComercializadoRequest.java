package com.backend.controller.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemComercializadoRequest(
        @NotBlank(message = "O nome do item não pode estar nulo/branco")
        @Size(max = 50, message = "O nome do item não pode ter mais que 50 caracteres")
        String nomeItem,

        @NotBlank(message = "O reino de origem não pode estar nulo/branco")
        @Size(max = 50, message = "O reino não pode ter mais que 50 caracteres")
        String reinoOrigem,

        @NotNull(message = "O preço base não pode estar nulo")
        @Positive(message = "O preço base deve ser maior que zero")
        @Digits(integer = 10, fraction = 2, message = "O preço base deve ter no máximo 10 dígitos inteiros e 2 dígitos decimais")
        BigDecimal precoBase
) {}
