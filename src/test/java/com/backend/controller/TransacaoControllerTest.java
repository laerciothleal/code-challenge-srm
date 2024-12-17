package com.backend.controller;

import com.backend.controller.request.CriarTransacaoRequest;
import com.backend.controller.response.TransacaoResponse;
import com.backend.mappper.TransacaoMapper;
import com.backend.model.Transacao;
import com.backend.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    @Mock
    private TransacaoMapper transacaoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        CriarTransacaoRequest request = new CriarTransacaoRequest(
                "peles", BigDecimal.TEN, "ouro real", "tibar"
        );

        Transacao transacao = Transacao.builder()
                .nomeItem("peles")
                .quantidade(BigDecimal.TEN)
                .moedaOrigem("ouro real")
                .moedaDestino("tibar")
                .valorConvertido(new BigDecimal("25.00"))
                .build();

        TransacaoResponse response = TransacaoResponse.builder()
                .nomeItem("peles")
//                .quantidade(BigDecimal.TEN)
                .moedaOrigem("ouro real")
                .moedaDestino("tibar")
                .valorConvertido(new BigDecimal("25.00"))
                .build();

        when(transacaoService.processTransaction(anyString(), any(BigDecimal.class), anyString(), anyString()))
                .thenReturn(transacao);
        when(transacaoMapper.toResponse(transacao)).thenReturn(response);

        ResponseEntity<TransacaoResponse> result = transacaoController.createTransaction(request);

        assertNotNull(result);
        assertEquals(response, result.getBody());
        verify(transacaoService).processTransaction("peles", BigDecimal.TEN, "ouro real", "tibar");
        verify(transacaoMapper).toResponse(transacao);
    }

    @Test
    void shouldSearchTransactionsSuccessfully() {
        String nomeItem = "peles";
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";

        Transacao transacao = Transacao.builder()
                .nomeItem("peles")
                .quantidade(BigDecimal.TEN)
                .moedaOrigem("ouro real")
                .moedaDestino("tibar")
                .valorConvertido(new BigDecimal("25.00"))
                .build();

        TransacaoResponse response = TransacaoResponse.builder()
                .nomeItem("peles")
                .quantidade(BigDecimal.TEN)
                .moedaOrigem("ouro real")
                .moedaDestino("tibar")
                .valorConvertido(new BigDecimal("25.00"))
                .build();

        when(transacaoService.findByNomeItemMoedaOrigemMoedaDestino(anyString(), anyString(), anyString()))
                .thenReturn(List.of(transacao));
        when(transacaoMapper.toResponseList(anyList())).thenReturn(List.of(response));

        ResponseEntity<List<TransacaoResponse>> result = transacaoController.searchTransactions(nomeItem, moedaOrigem, moedaDestino);

        assertNotNull(result);
        assertEquals(1, result.getBody().size());
        assertEquals(response, result.getBody().get(0));
        verify(transacaoService).findByNomeItemMoedaOrigemMoedaDestino(nomeItem, moedaOrigem, moedaDestino);
        verify(transacaoMapper).toResponseList(List.of(transacao));
    }
}
