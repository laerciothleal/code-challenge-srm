package com.backend.controller.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemComercializadoResponse(
        Long id,
        String nomeItem,
        String reinoOrigem,
        BigDecimal precoBase
) {}
