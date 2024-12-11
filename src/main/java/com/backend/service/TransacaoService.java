package com.backend.service;

import com.backend.exception.ItemComercializadoNotFoundException;
import com.backend.model.Transacao;
import com.backend.repository.ItemComercializadoRepository;
import com.backend.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ItemComercializadoRepository itemComercializadoRepository;
    private final MoedaService moedaService;


    @Transactional
    public Transacao processTransaction(String nomeItem, BigDecimal valor, String moedaOrigem, String moedaDestino) {

        // Verificar se o item existe no banco
        if (!itemComercializadoRepository.existsByNomeItemIgnoreCase(nomeItem)) {
            throw new ItemComercializadoNotFoundException(nomeItem);
        }

        BigDecimal valorConvertido = moedaService.convert(valor, moedaOrigem, moedaDestino);

        Transacao transacao = Transacao.builder()
                .dataTransacao(LocalDateTime.now())
                .nomeItem(nomeItem.toLowerCase())
                .moedaOrigem(moedaOrigem.toLowerCase())
                .moedaDestino(moedaDestino.toLowerCase())
                .valor(valor)
                .valorConvertido(valorConvertido)
                .build();

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> findByNomeItemMoedaOrigemMoedaDestino(String nomeItem, String moedaOrigem, String moedaDestino) {
        return transacaoRepository.findingCustomized(nomeItem, moedaOrigem, moedaDestino);
    }
}
