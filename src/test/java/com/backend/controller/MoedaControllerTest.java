package com.backend.controller;

import com.backend.controller.request.MoedaRequest;
import com.backend.controller.response.MoedaResponse;
import com.backend.mappper.MoedaMapper;
import com.backend.model.Moeda;
import com.backend.service.MoedaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MoedaControllerTest {

    @InjectMocks
    private MoedaController moedaController;

    @Mock
    private MoedaService moedaService;

    @Mock
    private MoedaMapper moedaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConvertCurrency() {
        BigDecimal valor = BigDecimal.valueOf(100);
        String moedaOrigem = "ouro real";
        String moedaDestino = "tibar";
        BigDecimal valorConvertido = BigDecimal.valueOf(250);

        when(moedaService.convert(valor, moedaOrigem, moedaDestino)).thenReturn(valorConvertido);

        ResponseEntity<BigDecimal> response = moedaController.convert(valor, moedaOrigem, moedaDestino);

        assertThat(response.getBody()).isEqualTo(valorConvertido);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(moedaService, times(1)).convert(valor, moedaOrigem, moedaDestino);
    }

    @Test
    void shouldSaveMoeda() {
        MoedaRequest request = MoedaRequest.builder()
                .nomeMoeda("ouro real")
                .taxaCambio(BigDecimal.ONE)
                .build();
        Moeda moeda = Moeda.builder()
                .nomeMoeda("ouro real")
                .taxaCambio(BigDecimal.ONE)
                .build();
        MoedaResponse response = new MoedaResponse(1L, "ouro real", BigDecimal.ONE);

        when(moedaMapper.toEntity(request)).thenReturn(moeda);
        when(moedaService.save(moeda)).thenReturn(moeda);
        when(moedaMapper.toResponse(moeda)).thenReturn(response);

        ResponseEntity<MoedaResponse> result = moedaController.save(request);

        assertThat(result.getBody()).isEqualTo(response);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        verify(moedaService, times(1)).save(moeda);
    }

    @Test
    void shouldUpdateMoeda() {
        Long id = 1L;
        MoedaRequest request = MoedaRequest.builder()
                .nomeMoeda("tibar")
                .taxaCambio(BigDecimal.valueOf(0.4))
                .build();
        Moeda moeda = Moeda.builder()
                .nomeMoeda("tibar")
                .taxaCambio(BigDecimal.valueOf(0.4))
                .build();
        MoedaResponse response = new MoedaResponse(1L, "tibar", BigDecimal.valueOf(0.4));

        when(moedaMapper.toEntity(request)).thenReturn(moeda);
        when(moedaService.update(id, moeda)).thenReturn(moeda);
        when(moedaMapper.toResponse(moeda)).thenReturn(response);

        ResponseEntity<MoedaResponse> result = moedaController.update(id, request);

        assertThat(result.getBody()).isEqualTo(response);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        verify(moedaService, times(1)).update(id, moeda);
    }

    @Test
    void shouldFindMoedaById() {
        Long id = 1L;
        Moeda moeda = Moeda.builder()
                .nomeMoeda("ouro real")
                .taxaCambio(BigDecimal.ONE)
                .build();
        MoedaResponse response = new MoedaResponse(1L, "ouro real", BigDecimal.ONE);

        when(moedaService.findById(id)).thenReturn(moeda);
        when(moedaMapper.toResponse(moeda)).thenReturn(response);

        ResponseEntity<MoedaResponse> result = moedaController.findById(id);

        assertThat(result.getBody()).isEqualTo(response);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        verify(moedaService, times(1)).findById(id);
    }

    @Test
    void shouldFindAllMoedas() {
        Moeda moeda1 = Moeda.builder()
                .nomeMoeda("ouro real")
                .taxaCambio(BigDecimal.ONE)
                .build();
        Moeda moeda2 = Moeda.builder()
                .nomeMoeda("tibar")
                .taxaCambio(BigDecimal.valueOf(0.4))
                .build();

        MoedaResponse response1 = new MoedaResponse(1L, "ouro real", BigDecimal.ONE);
        MoedaResponse response2 = new MoedaResponse(2L, "tibar", BigDecimal.valueOf(0.4));

        when(moedaService.findAll()).thenReturn(List.of(moeda1, moeda2));
        when(moedaMapper.toResponse(moeda1)).thenReturn(response1);
        when(moedaMapper.toResponse(moeda2)).thenReturn(response2);

        ResponseEntity<List<MoedaResponse>> result = moedaController.findAll();

        assertThat(result.getBody()).containsExactly(response1, response2);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        verify(moedaService, times(1)).findAll();
    }

    @Test
    void shouldDeleteMoedaById() {
        Long id = 1L;

        doNothing().when(moedaService).deleteById(id);

        ResponseEntity<Void> result = moedaController.deleteById(id);

        assertThat(result.getStatusCodeValue()).isEqualTo(204);
        verify(moedaService, times(1)).deleteById(id);
    }
}