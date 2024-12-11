package com.backend.controller;

import com.backend.controller.request.ItemComercializadoRequest;
import com.backend.controller.response.ItemComercializadoResponse;
import com.backend.mappper.ItemComercializadoMapper;
import com.backend.model.ItemComercializado;
import com.backend.service.ItemComercializadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/itens-comercializados")
@RequiredArgsConstructor
@Tag(name = "Controller Item Comercializado ", description = "API para gerenciamento de itens comercializados")
public class ItemComercializadoController {

    private final ItemComercializadoService service;
    private final ItemComercializadoMapper mapper;

    @Operation(summary = "Criar um item comercializado")
    @PostMapping
    public ResponseEntity<ItemComercializadoResponse> save(@Valid @RequestBody ItemComercializadoRequest request) {
        ItemComercializado item = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(item)));
    }

    @Operation(summary = "Atualizar um item comercializado")
    @PatchMapping("/{id}")
    public ResponseEntity<ItemComercializadoResponse> update(@PathVariable Long id, @Valid @RequestBody ItemComercializadoRequest request) {
        ItemComercializado item = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.update(id, item)));
    }

    @Operation(summary = "Buscar item comercializado por Id")
    @GetMapping("/{id}")
    public ResponseEntity<ItemComercializadoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(service.findById(id)));
    }

    @Operation(summary = "Listar todos os itens comercializados")
    @GetMapping
    public ResponseEntity<List<ItemComercializadoResponse>> findAll() {
        List<ItemComercializado> items = service.findAll();
        return ResponseEntity.ok(items.stream().map(mapper::toResponse).toList());
    }

    @Operation(summary = "Excluir um item comercializado por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
