package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_transacao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime dataTransacao;

    @Column(name = "nome_item", nullable = false)
    private String nomeItem;

    @Column(name = "moeda_origem", nullable = false)
    private String moedaOrigem;

    @Column(name = "moeda_destino", nullable = false)
    private String moedaDestino;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "valor_convertido", nullable = false)
    private BigDecimal valorConvertido;
}

