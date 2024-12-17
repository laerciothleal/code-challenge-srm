package com.backend.controller.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ConversaoResponse(

        BigDecimal valorConvertido
) {}
