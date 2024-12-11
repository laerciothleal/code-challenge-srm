package com.backend.service;

import com.backend.exception.ItemComercializadoNotFoundException;
import com.backend.model.Transacao;
import com.backend.repository.ItemComercializadoRepository;
import com.backend.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private ItemComercializadoRepository itemComercializadoRepository;

    @Mock
    private MoedaService moedaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldProcessTransactionSuccessfully() {
        String nomeItem = "peles";
        BigDecimal valor = BigDecimal.TEN;
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";
        BigDecimal valorConvertido = new BigDecimal("25.00");

        when(itemComercializadoRepository.existsByNomeItemIgnoreCase(nomeItem)).thenReturn(true);
        when(moedaService.convert(valor, moedaOrigem, moedaDestino)).thenReturn(valorConvertido);

        Transacao transacao = Transacao.builder()
                .dataTransacao(LocalDateTime.now())
                .nomeItem(nomeItem)
                .moedaOrigem(moedaOrigem)
                .moedaDestino(moedaDestino)
                .valor(valor)
                .valorConvertido(valorConvertido)
                .build();

        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao result = transacaoService.processTransaction(nomeItem, valor, moedaOrigem, moedaDestino);

        assertNotNull(result);
        assertEquals(nomeItem.toLowerCase(), result.getNomeItem());
        assertEquals(valor, result.getValor());
        assertEquals(moedaOrigem.toLowerCase(), result.getMoedaOrigem());
        assertEquals(moedaDestino.toLowerCase(), result.getMoedaDestino());
        assertEquals(valorConvertido, result.getValorConvertido());

        verify(itemComercializadoRepository).existsByNomeItemIgnoreCase(nomeItem);
        verify(moedaService).convert(valor, moedaOrigem, moedaDestino);
        verify(transacaoRepository).save(any(Transacao.class));
    }

    @Test
    void shouldThrowExceptionWhenItemNotFound() {
        String nomeItem = "inexistente";
        BigDecimal valor = BigDecimal.TEN;
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";

        when(itemComercializadoRepository.existsByNomeItemIgnoreCase(nomeItem)).thenReturn(false);

        assertThrows(ItemComercializadoNotFoundException.class,
                () -> transacaoService.processTransaction(nomeItem, valor, moedaOrigem, moedaDestino));

        verify(itemComercializadoRepository).existsByNomeItemIgnoreCase(nomeItem);
        verifyNoInteractions(moedaService, transacaoRepository);
    }

    @Test
    void shouldFindTransactionsSuccessfully() {
        String nomeItem = "peles";
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";

        Transacao transacao = Transacao.builder()
                .dataTransacao(LocalDateTime.now())
                .nomeItem(nomeItem)
                .moedaOrigem(moedaOrigem)
                .moedaDestino(moedaDestino)
                .valor(BigDecimal.TEN)
                .valorConvertido(new BigDecimal("25.00"))
                .build();

        when(transacaoRepository.findingCustomized(nomeItem, moedaOrigem, moedaDestino))
                .thenReturn(List.of(transacao));

        List<Transacao> result = transacaoService.findByNomeItemMoedaOrigemMoedaDestino(nomeItem, moedaOrigem, moedaDestino);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(nomeItem, result.get(0).getNomeItem());
        assertEquals(moedaOrigem, result.get(0).getMoedaOrigem());
        assertEquals(moedaDestino, result.get(0).getMoedaDestino());

        verify(transacaoRepository).findingCustomized(nomeItem, moedaOrigem, moedaDestino);
    }
}
