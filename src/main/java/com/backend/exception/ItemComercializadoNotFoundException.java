package com.backend.exception;

public class ItemComercializadoNotFoundException extends RuntimeException {

    public ItemComercializadoNotFoundException(String itemName) {
        super(String.format("Item Comercializado de troca '%s' não foi encontrado no sistema.", itemName));
    }

    public ItemComercializadoNotFoundException(Long id) {
        super(String.format("Item Comercializado com id '%s' não encontrada no sistema.", id));
    }
}
