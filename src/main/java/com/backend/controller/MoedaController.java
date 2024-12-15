package com.backend.controller;

import com.backend.controller.request.MoedaRequest;
import com.backend.controller.response.ConversaoResponse;
import com.backend.controller.response.MoedaResponse;
import com.backend.mappper.ConversaoMapper;
import com.backend.mappper.MoedaMapper;
import com.backend.model.Moeda;
import com.backend.service.MoedaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/moeda")
@Tag(name = "Controller de Taxa de Câmbio", description = "API para gerenciar taxas de câmbio.")
public class MoedaController {

    private final MoedaService moedaService;
    private final MoedaMapper moedaMapper;
    private final ConversaoMapper conversaoMapper;


    @Operation(summary = "Conversão de moeda", description = "Realiza conversão de moeda com as informações fornecidas.")
    @GetMapping("/converter")
    public ResponseEntity<ConversaoResponse> convert(
            @Parameter(description = "Valor", example = "10.0")
            @RequestParam BigDecimal valor,
            @Parameter(description = "moedaOrigem", example = "tibar")
            @RequestParam String moedaOrigem,
            @Parameter(description = "moedaDestino", example = "ouro real")
            @RequestParam String moedaDestino) {

        BigDecimal valorConvertido = moedaService.convert(valor, moedaOrigem, moedaDestino);

        ConversaoResponse response = conversaoMapper.toConversaoResponse(valorConvertido);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Criar uma moeda")
    @PostMapping
    public ResponseEntity<MoedaResponse> save(@Valid @RequestBody MoedaRequest request) {
        Moeda moeda = moedaMapper.toEntity(request);
        return ResponseEntity.ok(moedaMapper.toResponse(moedaService.save(moeda)));
    }

    @Operation(summary = "Atualizar uma moeda")
    @PatchMapping("/{id}")
    public ResponseEntity<MoedaResponse> update(@PathVariable Long id, @Valid @RequestBody MoedaRequest request) {
        Moeda moeda = moedaMapper.toEntity(request);
        return ResponseEntity.ok(moedaMapper.toResponse(moedaService.update(id, moeda)));
    }

    @Operation(summary = "Buscar moeda por Id")
    @GetMapping("/{id}")
    public ResponseEntity<MoedaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(moedaMapper.toResponse(moedaService.findById(id)));
    }

    @Operation(summary = "Listar todas as moedas")
    @GetMapping
    public ResponseEntity<List<MoedaResponse>> findAll() {
        List<Moeda> moedas = moedaService.findAll();
        return ResponseEntity.ok(moedas.stream()
                .map(moedaMapper::toResponse)
                .toList());
    }

    @Operation(summary = "Excluir uma moeda por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        moedaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
