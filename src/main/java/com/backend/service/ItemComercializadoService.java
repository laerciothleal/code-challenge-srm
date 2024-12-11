package com.backend.service;

import com.backend.exception.ItemComercializadoNotFoundException;
import com.backend.model.ItemComercializado;
import com.backend.repository.ItemComercializadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemComercializadoService {

    private final ItemComercializadoRepository repository;

    public ItemComercializado save(ItemComercializado itemComercializado) {
        return repository.save(itemComercializado);
    }

    public ItemComercializado update(Long id, ItemComercializado itemComercializado) {
        ItemComercializado existingItem = findById(id);
        existingItem.setNomeItem(itemComercializado.getNomeItem());
        existingItem.setReinoOrigem(itemComercializado.getReinoOrigem());
        existingItem.setPrecoBase(itemComercializado.getPrecoBase());
        return repository.save(existingItem);
    }

    public ItemComercializado findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ItemComercializadoNotFoundException(id));
    }

    public List<ItemComercializado> findAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
