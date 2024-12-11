package com.backend.service;

import com.backend.exception.ItemComercializadoNotFoundException;
import com.backend.model.ItemComercializado;
import com.backend.repository.ItemComercializadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemComercializadoServiceTest {

    @Mock
    private ItemComercializadoRepository repository;

    @InjectMocks
    private ItemComercializadoService service;

    @Test
    void shouldSaveItemComercializado() {
        ItemComercializado item = new ItemComercializado(null, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        when(repository.save(item)).thenReturn(item);

        ItemComercializado savedItem = service.save(item);

        assertNotNull(savedItem);
        assertEquals("Peles", savedItem.getNomeItem());
        verify(repository, times(1)).save(item);
    }

    @Test
    void shouldUpdateItemComercializado() {
        ItemComercializado existingItem = new ItemComercializado(1L, "Madeira", "Reino da Floresta", BigDecimal.valueOf(50.0));
        ItemComercializado updatedItem = new ItemComercializado(null, "Hidromel", "Reino dos Vales", BigDecimal.valueOf(30.0));

        when(repository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(repository.save(existingItem)).thenReturn(existingItem);

        ItemComercializado result = service.update(1L, updatedItem);

        assertNotNull(result);
        assertEquals("Hidromel", result.getNomeItem());
        assertEquals("Reino dos Vales", result.getReinoOrigem());
        assertEquals(BigDecimal.valueOf(30.0), result.getPrecoBase());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(existingItem);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingItem() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemComercializadoNotFoundException.class, () -> service.update(1L, new ItemComercializado()));
        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any());
    }

    @Test
    void shouldFindItemById() {
        ItemComercializado item = new ItemComercializado(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0));
        when(repository.findById(1L)).thenReturn(Optional.of(item));

        ItemComercializado result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Peles", result.getNomeItem());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenItemNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ItemComercializadoNotFoundException.class, () -> service.findById(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldFindAllItems() {
        List<ItemComercializado> items = List.of(
                new ItemComercializado(1L, "Peles", "Reino das Montanhas", BigDecimal.valueOf(100.0)),
                new ItemComercializado(2L, "Madeira", "Reino da Floresta", BigDecimal.valueOf(50.0))
        );
        when(repository.findAll()).thenReturn(items);

        List<ItemComercializado> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldDeleteItemById() {
        doNothing().when(repository).deleteById(1L);

        service.deleteById(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
