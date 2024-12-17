package com.backend.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        BigDecimal quantidade,
        BigDecimal valorConvertido
) {}
