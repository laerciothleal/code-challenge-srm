package com.backend.mappper;

import com.backend.controller.request.MoedaRequest;
import com.backend.controller.response.MoedaResponse;
import com.backend.model.Moeda;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        builder = @Builder
)
public interface MoedaMapper {

    Moeda toEntity(MoedaRequest request);

    MoedaResponse toResponse(Moeda moeda);
}
