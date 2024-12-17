package com.backend.service;

import com.backend.exception.ItemComercializadoNotFoundException;
import com.backend.model.ItemComercializado;
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
import java.util.Optional;

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
        BigDecimal quantidade = BigDecimal.TEN;
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";
        BigDecimal precoBase = BigDecimal.valueOf(50.00); // Preço base do produto
        BigDecimal valorConvertido = BigDecimal.valueOf(1250.00); // 10 * 50 convertido

        // Mock do Item
        ItemComercializado item = ItemComercializado.builder()
                .nomeItem(nomeItem)
                .precoBase(precoBase)
                .build();

        when(itemComercializadoRepository.findByNomeItemIgnoreCase(nomeItem))
                .thenReturn(Optional.of(item)); // Retorna o item encontrado

        when(moedaService.convert(precoBase.multiply(quantidade), moedaOrigem, moedaDestino))
                .thenReturn(valorConvertido);

        Transacao transacao = Transacao.builder()
                .dataTransacao(LocalDateTime.now())
                .nomeItem(nomeItem)
                .moedaOrigem(moedaOrigem.toLowerCase())
                .moedaDestino(moedaDestino.toLowerCase())
                .quantidade(quantidade)
                .valorConvertido(valorConvertido)
                .build();

        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacao);

        Transacao result = transacaoService.processTransaction(nomeItem, quantidade, moedaOrigem, moedaDestino);

        assertNotNull(result);
        assertEquals(nomeItem.toLowerCase(), result.getNomeItem());
        assertEquals(quantidade, result.getQuantidade());
        assertEquals(moedaOrigem.toLowerCase(), result.getMoedaOrigem());
        assertEquals(moedaDestino.toLowerCase(), result.getMoedaDestino());
        assertEquals(valorConvertido, result.getValorConvertido());

        verify(itemComercializadoRepository).findByNomeItemIgnoreCase(nomeItem);
        verify(moedaService).convert(precoBase.multiply(quantidade), moedaOrigem, moedaDestino);
        verify(transacaoRepository).save(any(Transacao.class));
    }


    @Test
    void shouldThrowExceptionWhenItemNotFound() {
        String nomeItem = "inexistente";
        BigDecimal quantidade = BigDecimal.TEN;
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";

        when(itemComercializadoRepository.findByNomeItemIgnoreCase(nomeItem))
                .thenReturn(Optional.empty()); // Simula item não encontrado

        assertThrows(ItemComercializadoNotFoundException.class,
                () -> transacaoService.processTransaction(nomeItem, quantidade, moedaOrigem, moedaDestino));

        verify(itemComercializadoRepository).findByNomeItemIgnoreCase(nomeItem);
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
                .quantidade(BigDecimal.TEN)
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
