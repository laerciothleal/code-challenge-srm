package com.backend.mappper;

import com.backend.controller.response.ConversaoResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        builder = @Builder
)
public interface ConversaoMapper {


    default ConversaoResponse toConversaoResponse(BigDecimal valorConvertido) {
        return new ConversaoResponse(valorConvertido);
    }
}
