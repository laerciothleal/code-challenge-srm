package com.backend.controller;

import com.backend.controller.request.CriarTransacaoRequest;
import com.backend.controller.response.TransacaoResponse;
import com.backend.mappper.TransacaoMapper;
import com.backend.model.Transacao;
import com.backend.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacao-moeda")
@Tag(name = "Controller Transação de Moeda", description = "API para gerenciar transação de moeda.")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final TransacaoMapper transacaoMapper;


    @Operation(summary = "Criar uma transação", description = "Cria uma transação com base nas informações fornecidas.")
    @PostMapping
    public ResponseEntity<TransacaoResponse> createTransaction(@Valid @RequestBody CriarTransacaoRequest request) {
        Transacao transacao = transacaoService.processTransaction(
                request.nomeItem(),
                request.quantidade(),
                request.moedaOrigem(),
                request.moedaDestino()
        );

        return ResponseEntity.ok(transacaoMapper.toResponse(transacao));
    }

    @Operation(summary = "Buscar transações", description = "Busca transações conforme os parâmetros fornecidos.")
    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> searchTransactions(
            @Parameter(description = "Nome do Item", example = "peles")
            @RequestParam(required = false) String nomeItem,
            @Parameter(description = "Moeda de Origem", example = "tibar")
            @RequestParam(required = false) String moedaOrigem,
            @Parameter(description = "Moeda de Destino", example = "ouro real")
            @RequestParam(required = false) String moedaDestino) {

        List<Transacao> transactions = transacaoService.findByNomeItemMoedaOrigemMoedaDestino(nomeItem, moedaOrigem, moedaDestino);
        List<TransacaoResponse> responseList = transacaoMapper.toResponseList(transactions);

        return ResponseEntity.ok(responseList);
    }
}