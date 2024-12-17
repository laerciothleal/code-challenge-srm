package com.backend.repository;

import com.backend.model.ItemComercializado;
import com.backend.model.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemComercializadoRepository extends JpaRepository<ItemComercializado, Long> {

    boolean existsByNomeItemIgnoreCase(String nomeItem);
    Optional<ItemComercializado> findByNomeItemIgnoreCase(String nomeItem);
}
