package com.backend.mappper;

import com.backend.controller.response.TransacaoResponse;
import com.backend.model.Transacao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-14T11:05:00-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class TransacaoMapperImpl implements TransacaoMapper {

    @Override
    public TransacaoResponse toResponse(Transacao transacao) {
        if ( transacao == null ) {
            return null;
        }

        TransacaoResponse.TransacaoResponseBuilder transacaoResponse = TransacaoResponse.builder();

        if ( transacao.getId() != null ) {
            transacaoResponse.id( transacao.getId() );
        }
        if ( transacao.getDataTransacao() != null ) {
            transacaoResponse.dataTransacao( transacao.getDataTransacao() );
        }
        if ( transacao.getNomeItem() != null ) {
            transacaoResponse.nomeItem( transacao.getNomeItem() );
        }
        if ( transacao.getMoedaOrigem() != null ) {
            transacaoResponse.moedaOrigem( transacao.getMoedaOrigem() );
        }
        if ( transacao.getMoedaDestino() != null ) {
            transacaoResponse.moedaDestino( transacao.getMoedaDestino() );
        }
        if ( transacao.getValor() != null ) {
            transacaoResponse.valor( transacao.getValor() );
        }
        if ( transacao.getValorConvertido() != null ) {
            transacaoResponse.valorConvertido( transacao.getValorConvertido() );
        }

        return transacaoResponse.build();
    }

    @Override
    public List<TransacaoResponse> toResponseList(List<Transacao> transacoes) {
        if ( transacoes == null ) {
            return null;
        }

        List<TransacaoResponse> list = new ArrayList<TransacaoResponse>( transacoes.size() );
        for ( Transacao transacao : transacoes ) {
            list.add( toResponse( transacao ) );
        }

        return list;
    }
}
