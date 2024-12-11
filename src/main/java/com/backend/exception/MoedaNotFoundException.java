package com.backend.exception;

public class MoedaNotFoundException extends RuntimeException {

    public MoedaNotFoundException(String moeda) {
        super(String.format("Moeda '%s' não encontrada no sistema.", moeda));
    }
    public MoedaNotFoundException(Long id) {
        super(String.format("Moeda com id '%s' não encontrada no sistema.", id));
    }
}