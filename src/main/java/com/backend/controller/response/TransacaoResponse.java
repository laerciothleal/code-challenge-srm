package com.backend.controller.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransacaoResponse(
        Long id,
        LocalDateTime dataTransacao,
        String nomeItem,
        String moedaOrigem,
        String moedaDestino,
        BigDecimal valor,
        BigDecimal valorConvertido
) {}
