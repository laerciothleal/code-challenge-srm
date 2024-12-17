package com.backend.service;

import com.backend.exception.MoedaNotFoundException;
import com.backend.model.Moeda;
import com.backend.repository.MoedaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoedaService {

    private final MoedaRepository moedaRepository;

    public BigDecimal convert(BigDecimal valor, String moedaOrigem, String moedaDestino) {

        Moeda de = moedaRepository.findByNomeMoedaIgnoreCase(moedaOrigem.trim())
                .orElseThrow(() -> new MoedaNotFoundException(moedaOrigem));

        Moeda para = moedaRepository.findByNomeMoedaIgnoreCase(moedaDestino.trim())
                .orElseThrow(() -> new MoedaNotFoundException(moedaDestino));

        return valor.multiply(para.getTaxaCambio().divide(de.getTaxaCambio(), MathContext.DECIMAL128));
    }
    public Moeda save(Moeda moeda) {
        return moedaRepository.save(moeda);
    }

    public Moeda update(Long id, Moeda moeda) {
        Moeda existingMoeda = findById(id);
        existingMoeda.setNomeMoeda(moeda.getNomeMoeda());
        existingMoeda.setTaxaCambio(moeda.getTaxaCambio());
        return moedaRepository.save(existingMoeda);
    }

    public Moeda findById(Long id) {
        return moedaRepository.findById(id)
                .orElseThrow(() -> new MoedaNotFoundException(id));
    }

    public List<Moeda> findAll() {
        return moedaRepository.findAll();
    }

    public void deleteById(Long id) {
        moedaRepository.deleteById(id);
    }
}
