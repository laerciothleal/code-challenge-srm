package com.backend.controller;

import com.backend.controller.request.ItemComercializadoRequest;
import com.backend.controller.response.ItemComercializadoResponse;
import com.backend.mappper.ItemComercializadoMapper;
import com.backend.model.ItemComercializado;
import com.backend.service.ItemComercializadoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemComercializadoControllerTest {

    @Mock
    private ItemComercializadoService service;

    @Mock
    private ItemComercializadoMapper mapper;

    @InjectMocks
    private ItemComercializadoController controller;

    @Test
    void shouldSaveItemComercializado() {
        ItemComercializadoRequest request = new ItemComercializadoRequest("Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        ItemComercializado item = new ItemComercializado(null, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        ItemComercializadoResponse response = new ItemComercializadoResponse(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));

        when(mapper.toEntity(request)).thenReturn(item);
        when(service.save(item)).thenReturn(item);
        when(mapper.toResponse(item)).thenReturn(response);

        ResponseEntity<ItemComercializadoResponse> result = controller.save(request);

        assertNotNull(result);
        verify(service, times(1)).save(item);
        verify(mapper, times(1)).toEntity(request);
        verify(mapper, times(1)).toResponse(item);
    }

    @Test
    void shouldUpdateItemComercializado() {
        Long id = 1L;
        ItemComercializadoRequest request = new ItemComercializadoRequest("Hidromel", "Reino dos Vales", BigDecimal.valueOf(30.0));
        ItemComercializado item = new ItemComercializado(null, "Hidromel", "Reino dos Vales", BigDecimal.valueOf(30.0));
        ItemComercializadoResponse response = new ItemComercializadoResponse(1L, "Hidromel", "Reino dos Vales", BigDecimal.valueOf(30.0));

        when(mapper.toEntity(request)).thenReturn(item);
        when(service.update(id, item)).thenReturn(item);
        when(mapper.toResponse(item)).thenReturn(response);

        ResponseEntity<ItemComercializadoResponse> result = controller.update(id, request);

        assertNotNull(result);
        verify(service, times(1)).update(id, item);
        verify(mapper, times(1)).toEntity(request);
        verify(mapper, times(1)).toResponse(item);
    }

    @Test
    void shouldFindItemById() {
        Long id = 1L;
        ItemComercializado item = new ItemComercializado(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        ItemComercializadoResponse response = new ItemComercializadoResponse(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));

        when(service.findById(id)).thenReturn(item);
        when(mapper.toResponse(item)).thenReturn(response);

        ResponseEntity<ItemComercializadoResponse> result = controller.findById(id);

        assertNotNull(result);
        verify(service, times(1)).findById(id);
        verify(mapper, times(1)).toResponse(item);
    }

    @Test
    void shouldFindAllItems() {
        ItemComercializado item = new ItemComercializado(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        ItemComercializadoResponse response = new ItemComercializadoResponse(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));

        when(service.findAll()).thenReturn(List.of(item));
        when(mapper.toResponse(item)).thenReturn(response);

        ResponseEntity<List<ItemComercializadoResponse>> result = controller.findAll();

        assertNotNull(result);
        assertEquals(1, result.getBody().size());
        verify(service, times(1)).findAll();
        verify(mapper, times(1)).toResponse(item);
    }

    @Test
    void shouldDeleteItemById() {
        Long id = 1L;

        doNothing().when(service).deleteById(id);

        ResponseEntity<Void> result = controller.deleteById(id);

        assertNotNull(result);
        assertEquals(204, result.getStatusCodeValue());
        verify(service, times(1)).deleteById(id);
    }
}
