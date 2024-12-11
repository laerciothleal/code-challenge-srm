package com.backend.mappper;

import com.backend.controller.request.ItemComercializadoRequest;
import com.backend.controller.response.ItemComercializadoResponse;
import com.backend.model.ItemComercializado;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        builder = @Builder
)
public interface ItemComercializadoMapper {

    ItemComercializado toEntity(ItemComercializadoRequest request);

    ItemComercializadoResponse toResponse(ItemComercializado itemComercializado);
}
