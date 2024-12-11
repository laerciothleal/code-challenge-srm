package com.backend.service;

import com.backend.exception.MoedaNotFoundException;
import com.backend.model.Moeda;
import com.backend.repository.MoedaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MoedaServiceTest {

    @InjectMocks
    private MoedaService moedaService;

    @Mock
    private MoedaRepository moedaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConvertValueSuccessfully() {
        Moeda moedaOrigem = new Moeda(1L, "Ouro Real", BigDecimal.ONE);
        Moeda moedaDestino = new Moeda(2L, "Tibar", BigDecimal.valueOf(0.4));

        when(moedaRepository.findByNomeMoedaIgnoreCase("Ouro Real")).thenReturn(Optional.of(moedaOrigem));
        when(moedaRepository.findByNomeMoedaIgnoreCase("Tibar")).thenReturn(Optional.of(moedaDestino));

        BigDecimal resultado = moedaService.convert(BigDecimal.valueOf(10), "Ouro Real", "Tibar");

        assertEquals(BigDecimal.valueOf(4).setScale(2), resultado.setScale(2));
        verify(moedaRepository).findByNomeMoedaIgnoreCase("Ouro Real");
        verify(moedaRepository).findByNomeMoedaIgnoreCase("Tibar");
    }

    @Test
    void shouldThrowExceptionWhenMoedaOrigemNotFound() {
        // Configurando o mock para retornar um valor vazio quando a moeda não é encontrada
        when(moedaRepository.findByNomeMoedaIgnoreCase("Inexistente")).thenReturn(Optional.empty());

        // Verifica se a exceção é lançada quando a moeda de origem não é encontrada
        assertThrows(MoedaNotFoundException.class,
                () -> moedaService.convert(BigDecimal.TEN, "Inexistente", "Tibar"));

        // Verifica se a interação ocorreu para a moeda de origem
        verify(moedaRepository).findByNomeMoedaIgnoreCase("Inexistente");

        // Não é necessário verificar a moeda de destino, pois o método não chega a acessá-la
        verifyNoMoreInteractions(moedaRepository);
    }


    @Test
    void shouldThrowExceptionWhenMoedaDestinoNotFound() {
        Moeda moedaOrigem = new Moeda(1L, "Ouro Real", BigDecimal.ONE);

        when(moedaRepository.findByNomeMoedaIgnoreCase("Ouro Real")).thenReturn(Optional.of(moedaOrigem));
        when(moedaRepository.findByNomeMoedaIgnoreCase("Inexistente")).thenReturn(Optional.empty());

        assertThrows(MoedaNotFoundException.class,
                () -> moedaService.convert(BigDecimal.TEN, "Ouro Real", "Inexistente"));

        verify(moedaRepository).findByNomeMoedaIgnoreCase("Ouro Real");
        verify(moedaRepository).findByNomeMoedaIgnoreCase("Inexistente");
    }

    @Test
    void shouldSaveMoedaSuccessfully() {
        Moeda moeda = new Moeda(null, "Ouro Real", BigDecimal.ONE);

        when(moedaRepository.save(moeda)).thenReturn(moeda);

        Moeda savedMoeda = moedaService.save(moeda);

        assertNotNull(savedMoeda);
        assertEquals("Ouro Real", savedMoeda.getNomeMoeda());
        verify(moedaRepository).save(moeda);
    }

    @Test
    void shouldUpdateMoedaSuccessfully() {
        Moeda existingMoeda = new Moeda(1L, "Ouro Real", BigDecimal.ONE);
        Moeda updatedMoeda = new Moeda(null, "Ouro Atualizado", BigDecimal.valueOf(2));

        when(moedaRepository.findById(1L)).thenReturn(Optional.of(existingMoeda));
        when(moedaRepository.save(existingMoeda)).thenReturn(existingMoeda);

        Moeda result = moedaService.update(1L, updatedMoeda);

        assertNotNull(result);
        assertEquals("Ouro Atualizado", result.getNomeMoeda());
        assertEquals(BigDecimal.valueOf(2), result.getTaxaCambio());
        verify(moedaRepository).findById(1L);
        verify(moedaRepository).save(existingMoeda);
    }

    @Test
    void shouldThrowExceptionWhenMoedaNotFound() {
        when(moedaRepository.findById(1L)).thenReturn(Optional.empty());

        Moeda moedaToUpdate = new Moeda(null, "Ouro Atualizado", BigDecimal.valueOf(2));

        assertThrows(MoedaNotFoundException.class, () -> moedaService.update(1L, moedaToUpdate));
        verify(moedaRepository).findById(1L);
        verifyNoMoreInteractions(moedaRepository);
    }

    @Test
    void shouldReturnMoedaSuccessfully() {
        Moeda moeda = new Moeda(1L, "Ouro Real", BigDecimal.ONE);

        when(moedaRepository.findById(1L)).thenReturn(Optional.of(moeda));

        Moeda result = moedaService.findById(1L);

        assertNotNull(result);
        assertEquals("Ouro Real", result.getNomeMoeda());
        verify(moedaRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenMoedaNotFoundSecondCase() {
        when(moedaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MoedaNotFoundException.class, () -> moedaService.findById(1L));
        verify(moedaRepository).findById(1L);
    }

    @Test
    void shouldDeleteMoedaSuccessfully() {
        doNothing().when(moedaRepository).deleteById(1L);

        moedaService.deleteById(1L);

        verify(moedaRepository).deleteById(1L);
    }

    @Test
    void shouldReturnAllMoedasSuccessfully() {
        List<Moeda> moedas = List.of(
                new Moeda(1L, "Ouro Real", BigDecimal.ONE),
                new Moeda(2L, "Tibar", BigDecimal.valueOf(0.4))
        );

        when(moedaRepository.findAll()).thenReturn(moedas);

        List<Moeda> result = moedaService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ouro Real", result.get(0).getNomeMoeda());
        assertEquals("Tibar", result.get(1).getNomeMoeda());

        verify(moedaRepository).findAll();
    }

}
