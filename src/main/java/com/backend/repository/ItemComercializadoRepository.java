package com.backend.repository;

import com.backend.model.ItemComercializado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemComercializadoRepository extends JpaRepository<ItemComercializado, Long> {

    boolean existsByNomeItemIgnoreCase(String nomeItem);
}
