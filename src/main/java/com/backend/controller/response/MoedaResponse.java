package com.backend.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MoedaResponse(
        Long id,
        String nomeMoeda,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
        BigDecimal taxaCambio
) {}
