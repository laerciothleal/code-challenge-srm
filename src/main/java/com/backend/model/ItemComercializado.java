package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item_comercializado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemComercializado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_item", nullable = false, unique = true)
    private String nomeItem;

    @Column(name = "reino_origem")
    private String reinoOrigem;

    @Column(name = "preco_base", nullable = false)
    private BigDecimal precoBase;
}
