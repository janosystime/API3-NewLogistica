package com.fleetops.backend.avaliacao.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa a avaliação de frete de um motorista.
 * Estruturada de forma isolada (pacote avaliacao) para facilitar
 * futura extração para um microsserviço de avaliações/ranking.
 *
 * Escala de notas:
 * 1 - Péssimo | 2 - Ruim | 3 - Regular | 4 - Bom | 5 - Excelente
 */
@Entity
@Table(name = "avaliacoes_frete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoFrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nota de 1 a 5 (obrigatória).
     * 1=Péssimo, 2=Ruim, 3=Regular, 4=Bom, 5=Excelente
     */
    @Column(nullable = false)
    private Integer nota;

    /**
     * Feedback textual da avaliação (obrigatório).
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String feedback;

    /**
     * ID do motorista ao qual a avaliação está vinculada.
     * Mantido como Long (sem @ManyToOne) para baixo acoplamento
     * e facilitar migração para microsserviço.
     */
    @Column(name = "motorista_id", nullable = false)
    private Long motoristaId;

    /**
     * Data/hora do registro da avaliação.
     */
    @Column(name = "data_registro", nullable = false, updatable = false)
    private LocalDateTime dataRegistro;

    @PrePersist
    protected void onCreate() {
        if (this.dataRegistro == null) {
            this.dataRegistro = LocalDateTime.now();
        }
    }
}
