package com.backend.mappper;

import com.backend.controller.request.ItemComercializadoRequest;
import com.backend.controller.response.ItemComercializadoResponse;
import com.backend.model.ItemComercializado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-11T08:08:31-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class ItemComercializadoMapperImpl implements ItemComercializadoMapper {

    @Override
    public ItemComercializado toEntity(ItemComercializadoRequest request) {
        if ( request == null ) {
            return null;
        }

        ItemComercializado.ItemComercializadoBuilder itemComercializado = ItemComercializado.builder();

        if ( request.nomeItem() != null ) {
            itemComercializado.nomeItem( request.nomeItem() );
        }
        if ( request.reinoOrigem() != null ) {
            itemComercializado.reinoOrigem( request.reinoOrigem() );
        }
        if ( request.precoBase() != null ) {
            itemComercializado.precoBase( request.precoBase() );
        }

        return itemComercializado.build();
    }

    @Override
    public ItemComercializadoResponse toResponse(ItemComercializado itemComercializado) {
        if ( itemComercializado == null ) {
            return null;
        }

        ItemComercializadoResponse.ItemComercializadoResponseBuilder itemComercializadoResponse = ItemComercializadoResponse.builder();

        if ( itemComercializado.getId() != null ) {
            itemComercializadoResponse.id( itemComercializado.getId() );
        }
        if ( itemComercializado.getNomeItem() != null ) {
            itemComercializadoResponse.nomeItem( itemComercializado.getNomeItem() );
        }
        if ( itemComercializado.getReinoOrigem() != null ) {
            itemComercializadoResponse.reinoOrigem( itemComercializado.getReinoOrigem() );
        }
        if ( itemComercializado.getPrecoBase() != null ) {
            itemComercializadoResponse.precoBase( itemComercializado.getPrecoBase() );
        }

        return itemComercializadoResponse.build();
    }
}
