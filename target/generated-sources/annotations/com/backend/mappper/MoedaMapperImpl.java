package com.backend.mappper;

import com.backend.controller.request.MoedaRequest;
import com.backend.controller.response.MoedaResponse;
import com.backend.model.Moeda;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T08:08:32-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class MoedaMapperImpl implements MoedaMapper {

    @Override
    public Moeda toEntity(MoedaRequest request) {
        if ( request == null ) {
            return null;
        }

        Moeda.MoedaBuilder moeda = Moeda.builder();

        if ( request.nomeMoeda() != null ) {
            moeda.nomeMoeda( request.nomeMoeda() );
        }
        if ( request.taxaCambio() != null ) {
            moeda.taxaCambio( request.taxaCambio() );
        }

        return moeda.build();
    }

    @Override
    public MoedaResponse toResponse(Moeda moeda) {
        if ( moeda == null ) {
            return null;
        }

        MoedaResponse.MoedaResponseBuilder moedaResponse = MoedaResponse.builder();

        if ( moeda.getId() != null ) {
            moedaResponse.id( moeda.getId() );
        }
        if ( moeda.getNomeMoeda() != null ) {
            moedaResponse.nomeMoeda( moeda.getNomeMoeda() );
        }
        if ( moeda.getTaxaCambio() != null ) {
            moedaResponse.taxaCambio( moeda.getTaxaCambio() );
        }

        return moedaResponse.build();
    }
}
