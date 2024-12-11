package com.backend.mappper;

import com.backend.controller.response.TransacaoResponse;
import com.backend.model.Transacao;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        builder = @Builder
)
public interface TransacaoMapper {

    TransacaoResponse toResponse(Transacao transacao);

    List<TransacaoResponse> toResponseList(List<Transacao> transacoes);
}
