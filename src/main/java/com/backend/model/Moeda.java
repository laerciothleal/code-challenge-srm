package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_moeda")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moeda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_moeda", nullable = false, unique = true)
    private String nomeMoeda;

    @Column(name = "taxa_cambio", nullable = false)
    private BigDecimal taxaCambio;
}
