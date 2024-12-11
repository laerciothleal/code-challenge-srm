package com.backend.repository;

import com.backend.model.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoedaRepository extends JpaRepository<Moeda, Long> {

    Optional<Moeda> findByNomeMoedaIgnoreCase(String nomeMoeda);
}
