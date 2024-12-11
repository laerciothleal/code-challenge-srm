package com.backend.repository;

import com.backend.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(
            """
            SELECT t FROM Transacao t WHERE  
            (( :nomeItem IS NULL OR LOWER(t.nomeItem) LIKE LOWER(CONCAT('%', :nomeItem, '%'))) 
            AND 
            (:moedaOrigem IS NULL OR LOWER(t.moedaOrigem) LIKE LOWER(CONCAT('%', :moedaOrigem, '%')))
            AND 
            (:moedaDestino IS NULL OR LOWER(t.moedaDestino) LIKE LOWER(CONCAT('%', :moedaDestino, '%'))))     
            """
    )
    List<Transacao> findingCustomized(
            @Param("nomeItem") String nomeItem,
            @Param("moedaOrigem") String moedaOrigem,
            @Param("moedaDestino") String moedaDestino);
}